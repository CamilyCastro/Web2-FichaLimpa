package edu.ifsp.fichaLimpa.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ifsp.fichaLimpa.model.Cidadao;
import edu.ifsp.fichaLimpa.model.Comentarios;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.model.Publicacao;
import edu.ifsp.fichaLimpa.model.User;
import edu.ifsp.fichaLimpa.repositorios.CidadaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.ComentariosRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PublicacaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.UserRepositorio;
import jakarta.validation.Valid;

@Controller
@RequestMapping(MappingController.Publicacao.MAIN)
@SessionAttributes("politico")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepositorio publicacaoRepositorio;

    @Autowired
    private CidadaoRepositorio cidadaoRepositorio;

    @Autowired
    private PoliticoRepositorio politicoRepo;

    @Autowired
    private UserRepositorio userRepo;

    @Autowired
    private ComentariosRepositorio comentariosRepositorio;

    @ModelAttribute("publicacao")
    public Publicacao publicacao(){
        return new Publicacao();
    }

    @GetMapping(MappingController.Publicacao.cadastro)
    public String viewPublicacao(Model model){

        return "publicacao-form";
    }

    @GetMapping(MappingController.Publicacao.listar)
    public String listarPublicacao(Model model){
        List<Publicacao> publicacoes = new ArrayList<>();
        publicacaoRepositorio.findAll().forEach(publicacoes::add);
        model.addAttribute("publicacoes", publicacoes);

        return "listar-publicacao";
    }

    @GetMapping(MappingController.Publicacao.edit + "/{id}")
    public String editarPublicacao(@PathVariable("id") Long id, Model model) {
        Optional<Publicacao> opt = publicacaoRepositorio.findById(id);

        if (opt.isPresent()) {

            Publicacao publicacao = opt.get();
            model.addAttribute("publicacao", publicacao);
        }

        return "editar-publicacao";
    }

    @GetMapping(MappingController.Publicacao.perfil + "/{id}")
    public String perfilPublicacao(@PathVariable("id") Long id, Model model,@AuthenticationPrincipal UserDetails userDetails){
        Optional<Publicacao> opt = publicacaoRepositorio.findById(id);

        boolean logado = true;
        
        if (opt.isPresent()) {

            Publicacao publicacao = opt.get();
            
            if(userDetails != null) {
            	
	            boolean isAdmin = userDetails.getAuthorities().stream()
	                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
	
	            if(userDetails.getUsername().equals(publicacao.getCidadao().getUser().getUsername()) || isAdmin) {
	            	model.addAttribute("permissao", true);
	            }
            }else {
            	logado = false;
            }
            
            //listar os comentarios
            List<Comentarios> comentarios = comentariosRepositorio.findByPublicacao(publicacao);
            model.addAttribute("comentarios", comentarios);
            model.addAttribute("comentar", true);
            model.addAttribute("publicacao", publicacao);
            model.addAttribute("logado", logado);
        }

        return "perfil-publicacao";
    }

    @GetMapping(MappingController.Publicacao.aprovar)
    public String viwFormAprovarPubli(Model model) {

    	List<Publicacao> emAprovacao = publicacaoRepositorio.findByAprovado(false);

    	model.addAttribute("publicacoes", emAprovacao);

    	return "aprovar-publi";
    }
    
    @GetMapping(MappingController.Publicacao.denunciar)
	public String viewFormAprovarComent(Model model) {

		List<Publicacao> emAprovacao = publicacaoRepositorio.findByDenunciar("analise");

		model.addAttribute("publicacoes", emAprovacao);

		return "aprovar-denuncia-publi";
	}


    @PostMapping(MappingController.Publicacao.cadastro + "/{id}")
    public String cadastrarPublicacao(@PathVariable("id") Long id, @Valid Publicacao publicacao, Errors errors, @AuthenticationPrincipal UserDetails userDetails, Model model, SessionStatus sessionStatus){
    	if (errors.hasErrors()){

    		model.addAttribute("publicacao", publicacao);
			return "publicacao-form";
    	}
    	System.out.println("entrei aqui");
        publicacao.setId(null);

        //DEFINIR DATA E HORA AUTOMATICAMENTE
        publicacao.setDataPublicacao(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        publicacao.setAprovado(false);
        publicacao.setDenunciar("aprovado");

        sessionStatus.setComplete();

        Optional<Politico> opt = politicoRepo.findById(id);

        if (opt.isPresent()) {

			Politico politico = opt.get();
			publicacao.setPolitico(politico);
		}

        Optional<User> optUser = userRepo.findById(userDetails.getUsername());

        if(optUser.isPresent()) {
        	User user = optUser.get();
        	Cidadao cidadao = user.getCidadao();
        	publicacao.setCidadao(cidadao);
        	publicacaoRepositorio.save(publicacao);
        	//model.addAttribute("sucess", true);
        }
        
		return "redirect:/politico/perfil/" + id ;
    }

	@PostMapping(MappingController.Publicacao.edit + "/{id}")
    public String editaPublicacao(@PathVariable("id") Long id, @Valid @ModelAttribute Publicacao publicacao, Errors errors, Model model ) {

		if (errors.hasErrors()){
			 List<Cidadao> cidadaos = new ArrayList<>();

		        cidadaoRepositorio.findAll().forEach(cidadaos::add);

		        model.addAttribute("publicacao", publicacao);
		        model.addAttribute("cidadaos", cidadaos);

			return "publicacao-form";
   	}

        Optional<Publicacao> optPubli = publicacaoRepositorio.findById(id);

        if(optPubli.isPresent()) {
        	Publicacao publi = optPubli.get();
        	publi.setDataPublicacao(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        	publi.setAnexo(publicacao.getAnexo());
        	publi.setAvaliacao(publicacao.getAvaliacao());
        	publi.setDescricao(publicacao.getDescricao());
        	publi.setTitulo(publicacao.getTitulo());


        	Optional<Cidadao> optCidadao = cidadaoRepositorio.findById(publi.getCidadao().getId());

            if(optCidadao.isPresent()) {

            	Cidadao cidadao = optCidadao.get();
            	cidadao.adicionarPubli(publi);
            	publicacaoRepositorio.save(publi);
            }


            System.out.println(publi);

            Optional<Politico> opt = politicoRepo.findById(publi.getPolitico().getId());

            if (opt.isPresent()) {

           	 	 Politico politico = opt.get();
        		 double notaPolitico = calcularNotaPolitico(politico);
        		 politico.setNota(notaPolitico);
        		 politicoRepo.save(politico);
        	}
        }

        return "redirect:/politico/perfil/" + id;
    }

    @PostMapping(MappingController.Publicacao.delete + "/{id}")
    public String deletePublicacao(@PathVariable("id") Long id) {
        try {
        	Optional<Publicacao> optPubli = publicacaoRepositorio.findById(id);

            if (optPubli.isPresent()) {
                Publicacao publi = optPubli.get();
                Politico politico = publi.getPolitico();

                publicacaoRepositorio.deleteById(id);

                double notaPolitico = calcularNotaPolitico(politico);
                politico.setNota(notaPolitico);
                politicoRepo.save(politico);
            }


        } catch (EmptyResultDataAccessException e) {}

        return "redirect:/politico/listar";
    }

    @PostMapping(MappingController.Publicacao.aprovar + "/{id}")
    public String aprovarPubli(@PathVariable("id") Long id, @RequestParam("status") boolean status, Publicacao publicacao) {

    	Optional<Publicacao> optPubli = publicacaoRepositorio.findById(id);

    	if (optPubli.isPresent()) {
            Publicacao publi = optPubli.get();

            if(status == true) {
            	publi.setAprovado(true);
                publicacaoRepositorio.save(publi);

            }else {
            	publicacaoRepositorio.delete(publi);
            }
 
            Optional<Politico> opt = politicoRepo.findById(publi.getPolitico().getId());

            if (opt.isPresent()) {

    			Politico politico = opt.get();

    			double notaPolitico = calcularNotaPolitico(politico);
    			politico.setNota(notaPolitico);
    			politicoRepo.save(politico);
    		}
    	}

    	return "redirect:/publicacao/aprovar";
    }
    
    @PostMapping(MappingController.Publicacao.aprovarDenuncia + "/{id}")
	public String denunciarPubli(@PathVariable("id") Long id, @RequestParam("status") String status) {

		Long idPolitico = null;
		
		Optional<Publicacao> opt = publicacaoRepositorio.findById(id);

		if (opt.isPresent()) {
			Publicacao publicacao = opt.get();
			
			idPolitico = publicacao.getPolitico().getId();

			if (status.equals("desaprovado")) {
				
				deletePublicacao(id);
				
			} else if (status.equals("aprovado")) {
				
				publicacao.setDenunciar(status);
				publicacaoRepositorio.save(publicacao);
				
			} else if(status.equals("analise")) {
				publicacao.setDenunciar(status);
				publicacaoRepositorio.save(publicacao);
				return "redirect:/publicacao/perfil/" + id;
			}
		}
		
		return "redirect:/politico/perfil/" + idPolitico;
    }

    private double calcularNotaPolitico(Politico politico) {
    	
		 List<Integer> notas = publicacaoRepositorio.findNotaByPolitico(politico.getId());
		 
		 if (notas.isEmpty()) {
		        return 0.0;
		 }

         int soma = 0;

         for(Integer notaP: notas) {
        	 soma += notaP;
         }
         
         double media = (double) soma / notas.size();

         return media;
	}
}
