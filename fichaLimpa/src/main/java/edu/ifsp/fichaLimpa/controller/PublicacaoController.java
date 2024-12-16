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

    @PostMapping(MappingController.Publicacao.cadastro + "/{id}")
    public String cadastrarPublicacao(@PathVariable("id") Long id, @Valid Publicacao publicacao, Errors errors,  SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "publicacao-form";
        }
        
        publicacao.setId(null);

        //DEFINIR DATA E HORA AUTOMATICAMENTE
        publicacao.setData_publicacao(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        
        Optional<Politico> opt = politicoRepo.findById(id);

        if (opt.isPresent()) {
			
			Politico politico = opt.get();	
			
			publicacao.setPolitico(politico);
	        
	        publicacaoRepositorio.save(publicacao);

	        sessionStatus.setComplete();
		}
		
		return "/home";
    }

    @PostMapping(MappingController.Publicacao.edit + "/{id}")
    public String salvarEditaPublicacao(@PathVariable("id") Long id, @Valid @ModelAttribute Publicacao publicacao, Errors errors) {
    	
        if(errors.hasErrors()) {
            return "editar-publicacao";
        }
        
        Optional<Publicacao> optPubli = publicacaoRepositorio.findById(id);
        
        if(!optPubli.isPresent()) {
        	return "/home";
        }
        
        Publicacao publiAtualizada = new Publicacao();
        publiAtualizada = optPubli.get();
        
        publiAtualizada.setData_publicacao(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        publiAtualizada.setTitulo(publicacao.getTitulo());
        publiAtualizada.setDescricao(publicacao.getDescricao());
        
        publicacaoRepositorio.save(publiAtualizada);

        return "/home";
    }

    @PostMapping(MappingController.Publicacao.delete + "/{id}")
    public String deletePublicacao(@PathVariable("id") Long id, Model model) {
        try {
            publicacaoRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}

        return "listar-publicacao";
    }
}
