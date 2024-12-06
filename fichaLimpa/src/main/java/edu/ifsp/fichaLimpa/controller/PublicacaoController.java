package edu.ifsp.fichaLimpa.controller;

import edu.ifsp.fichaLimpa.model.Cidadao;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.model.Proposta;
import edu.ifsp.fichaLimpa.model.Publicacao;
import edu.ifsp.fichaLimpa.repositorios.CidadaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PropostaRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PublicacaoRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(MappingController.Publicacao.MAIN)
@SessionAttributes("politico")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepositorio publicacaoRepositorio;

    @Autowired
    private CidadaoRepositorio cidadaoRepositorio;

    @Autowired
    private PoliticoRepositorio politicoRepositorio;

    @Autowired
    private PropostaRepositorio propostaRepositorio;

    @ModelAttribute("publicacao")
    public Publicacao publicacao(){
        return new Publicacao();
    }
    
    @ModelAttribute("politico")
    public Politico politico(){
        return new Politico();
    }

    @GetMapping(MappingController.Publicacao.cadastro + "/{id}")
    public String viewPublicacao(@PathVariable("id") Long id, Model model){
        List<Cidadao> cidadaos = new ArrayList<>();
        
        cidadaoRepositorio.findAll().forEach(cidadaos::add);
        
        Politico politico = new Politico();
       
        model.addAttribute("cidadaos",cidadaos);

       /*List<Politico> politicos = new ArrayList<>();
        politicoRepositorio.findAll().forEach(politicos::add);
        model.addAttribute("politicos",politicos);

        List<Proposta> propostas = new ArrayList<>();
        propostaRepositorio.findAll().forEach(propostas::add);
        model.addAttribute("propostas",propostas);*/

        return "publicacao-form";
    }

    @GetMapping(MappingController.Publicacao.listar)
    public String listarPublicacao(Model model){
        List<Publicacao> publicacoes = new ArrayList<>();
        publicacaoRepositorio.findAll().forEach(publicacoes::add);
        model.addAttribute("publicacoes", publicacoes);

        return "listar-publicacao";
    }

    @PostMapping(MappingController.Publicacao.cadastro + "/{id}")
    public String cadastrarPublicacao(@PathVariable("id") Long id, @Valid Publicacao publicacao, Errors errors,  SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "publicacao-form";
        }

        //DEFINIR DATA E HORA AUTOMATICAMENTE
        publicacao.setData_publicacao(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        
        Politico politico = new Politico();
        politico.setId(id);
        
        publicacao.setPolitico(politico);
        
        publicacaoRepositorio.save(publicacao);

        sessionStatus.setComplete();

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
