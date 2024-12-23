package edu.ifsp.fichaLimpa.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import edu.ifsp.fichaLimpa.model.Cidadao;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.model.Publicacao;
import edu.ifsp.fichaLimpa.repositorios.CidadaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PublicacaoRepositorio;
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

    @ModelAttribute("publicacao")
    public Publicacao publicacao(){
        return new Publicacao();
    }
    
    @GetMapping(MappingController.Publicacao.cadastro)
    public String viewPublicacao(Model model){
        List<Cidadao> cidadaos = new ArrayList<>();
        
        cidadaoRepositorio.findAll().forEach(cidadaos::add);
       
        model.addAttribute("cidadaos",cidadaos);

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

            List<Cidadao> cidadaos = new ArrayList<>();
            cidadaoRepositorio.findAll().forEach(cidadaos::add);

            model.addAttribute("cidadaos",cidadaos);
            model.addAttribute("publicacao", publicacao);


            return "editar-publicacao";
        }

        return "home";
    }

    @GetMapping(MappingController.Publicacao.perfil + "/{id}")
    public String perfilPublicacao(@PathVariable("id") Long id, Model model){
        Optional<Publicacao> opt = publicacaoRepositorio.findById(id);

        if (opt.isPresent()) {

            Publicacao publicacao = opt.get();
            model.addAttribute("publicacao", publicacao);

            return "perfil-publicacao";
        }

        return "home";
    }
    
    @GetMapping(MappingController.Publicacao.aprovar)
    public String viwFormaprovarPubli(Model model) {
    
    	List<Publicacao> emAprovacao = publicacaoRepositorio.findByAprovado(false);
    	
    	model.addAttribute("publicacoes", emAprovacao);
    	
    	return "aprovar-publi";
    }

    @PostMapping(MappingController.Publicacao.cadastro + "/{id}")
    public String cadastrarPublicacao(@PathVariable("id") Long id, @Valid Publicacao publicacao, Errors errors,  Model model, SessionStatus sessionStatus){
    	if (errors.hasErrors()){
			 List<Cidadao> cidadaos = new ArrayList<>();
			 
		        cidadaoRepositorio.findAll().forEach(cidadaos::add);
		        
		        model.addAttribute("publicacao", publicacao);
		        model.addAttribute("cidadaos", cidadaos);
		    			
			return "publicacao-form";
    	}
    	
        publicacao.setId(null);

        //DEFINIR DATA E HORA AUTOMATICAMENTE
        publicacao.setDataPublicacao(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        publicacao.setAprovado(false);
        
        sessionStatus.setComplete();
        
        Optional<Politico> opt = politicoRepo.findById(id);

        if (opt.isPresent()) {
			
			Politico politico = opt.get();	
			publicacao.setPolitico(politico);
			publicacaoRepositorio.save(publicacao);	        
		}
		
		return "/home";
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
        
        return "/home";
    }

    @PostMapping(MappingController.Publicacao.delete + "/{id}")
    public String deletePublicacao(@PathVariable("id") Long id, Model model) {
        try {
        	Optional<Publicacao> optPubli = publicacaoRepositorio.findById(id);
            
            if (optPubli.isPresent()) {
                Publicacao publi = optPubli.get();
                Politico politico = publi.getPolitico();

                // Deletar a publicação
                publicacaoRepositorio.deleteById(id);

                // Calcular e atualizar a nota do político
                double notaPolitico = calcularNotaPolitico(politico);
                politico.setNota(notaPolitico);
                politicoRepo.save(politico);
            }
            
            
        } catch (EmptyResultDataAccessException e) {}

        return "/home";
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
            
            Optional<Politico> opt = politicoRepo.findById(id);

            if (opt.isPresent()) {
    			
    			Politico politico = opt.get();	
    	  
    			double notaPolitico = calcularNotaPolitico(politico);
    			politico.setNota(notaPolitico);
    			politicoRepo.save(politico);	        
    		}         
    	}
    	
    	return "/home";
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
