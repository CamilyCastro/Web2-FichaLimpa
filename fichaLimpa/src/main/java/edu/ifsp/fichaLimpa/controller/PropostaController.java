package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;

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

@Controller
@SessionAttributes("politico")
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepositorio propostaRepositorio;
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	@ModelAttribute("proposta")
	private Proposta prosposta() {
		return new Proposta();
	}
	
	@ModelAttribute("politico")
	private Politico politico() {
		return new Politico();
	}

	@GetMapping
	public String viewProposta(Model model) {

		List<Categoria> categorias = new ArrayList<>();
		categoriaRepositorio.findAll().forEach(categorias::add);
		
		model.addAttribute("categoria", categorias);
		
		return "proposta-form";
	}

	@PostMapping
	public String salavarProposta(@Valid Proposta proposta, @ModelAttribute Politico politico, Errors errors) {
		
		if(errors.hasErrors()) {
			return "proposta-form";
		}
		
		proposta.setPolitico(politico);
		propostaRepositorio.save(proposta);
		
		return "/home";
	}

}
