package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ifsp.fichaLimpa.model.Categoria;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.model.Proposta;
import edu.ifsp.fichaLimpa.repositorios.CategoriaRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PropostaRepositorio;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("politico")
@RequestMapping(MappingController.Proposta.MAIN)
public class PropostaController {

    @Autowired
    private PropostaRepositorio propostaRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private PoliticoRepositorio politicoRepositorio;

    @ModelAttribute("proposta")
    private Proposta prosposta() {
        return new Proposta();
    }

    @ModelAttribute("politico")
    private Politico politico() {
        return new Politico();
    }

    @GetMapping(MappingController.Proposta.cadastro)
    public String viewProposta(Model model) {

        List<Categoria> categorias = new ArrayList<>();
        categoriaRepositorio.findAll().forEach(categorias::add);

        model.addAttribute("categorias", categorias);

        // salvar politico usando lista
        // para usar campo de texto é só deletar o codigo
        List<Politico> politicos = new ArrayList<>();
        politicoRepositorio.findAll().forEach(politicos::add);

        model.addAttribute("politicos", politicos);

        return "proposta-form";
    }

    @GetMapping(MappingController.Proposta.listar)
    public String listarPropostas(Model model) {

        List<Proposta> propostas = new ArrayList<>();
        propostaRepositorio.findAll().forEach(propostas::add);

        model.addAttribute("propostas", propostas);

        return "listar-proposta";
    }

    @PostMapping
    public String salvarProposta(@Valid Proposta proposta, @ModelAttribute Politico politico, Errors errors, SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
            return "proposta-form";
        }

        propostaRepositorio.save(proposta);

        sessionStatus.setComplete();

        return "/home";

        // ************ CODIGO PARA SALVAR O POLITICO USANDO CAMPO DE TEXTO DIGITANDO O NOME ***********************

        /*if (proposta.getPolitico() != null) {
            String nomePolitico = proposta.getPolitico().getNome();
            politico = politicoRepositorio.findByNome(nomePolitico);

            proposta.setPolitico(politico);
            propostaRepositorio.save(proposta);
        } else {
            return "/home";
        }*/
    }

}
