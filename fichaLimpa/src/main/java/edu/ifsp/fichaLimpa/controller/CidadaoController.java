package edu.ifsp.fichaLimpa.controller;

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
import edu.ifsp.fichaLimpa.model.Endereco;
import edu.ifsp.fichaLimpa.repositorios.CidadaoRepositorio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("cidadao")
@RequestMapping(MappingController.Cidadao.MAIN)
public class CidadaoController {

	@Autowired
	private CidadaoRepositorio cidadaoRepositorio;
	
	@ModelAttribute(name = "cidadao")
	public Cidadao cidadao() {
		return new Cidadao();
	}
	
	public Endereco endereco() {
		return new Endereco();
	}
	
	@GetMapping(MappingController.Cidadao.cadastro)
	public String saveCidadao(Model model) {
		Cidadao cidadao = new Cidadao();
		model.addAttribute("cidadao", cidadao);
		return "cidadao-form";
	}
	
	@GetMapping(MappingController.Cidadao.listar)
	public String listarCidadao(Model model) {
		List<Cidadao> cidadaos = new ArrayList<>();
		cidadaoRepositorio.findAll().forEach(cidadaos::add);
		
		model.addAttribute("cidadaos", cidadaos);
		return "listar-cidadao";
	}
	
	@GetMapping(MappingController.Cidadao.edit + "/{id}")
	public String formEditarCidadao(@PathVariable("id") Long id, Model model) {
		Optional<Cidadao> opt = cidadaoRepositorio.findById(id);		
		
		if (opt.isPresent()) {
			
			Cidadao cidadao = opt.get();
			model.addAttribute("cidadao", cidadao);
			
			return "editar-cidadao";
		}
		
		return "home";
	}
	
	@GetMapping(MappingController.Cidadao.perfil + "/{id}")
	public String perfilCidadao(@PathVariable("id") Long id, Model model){
		Optional<Cidadao> opt = cidadaoRepositorio.findById(id);		
		
		if (opt.isPresent()) {
			
			Cidadao cidadao = opt.get();
			model.addAttribute("cidadao", cidadao);
			
			return "perfil-cidadao";
		}
		
		return "home";
	}
	
	@PostMapping(MappingController.Cidadao.edit)
	public String editCidadao(@Valid @ModelAttribute Cidadao cidadao, Errors errors ) {
		if(errors.hasErrors()) {
			return "editar-cidadao";
		}
		
		if (cidadao.getEndereco() != null) {
	        cidadao.getEndereco().setCidadao(cidadao);
	        cidadaoRepositorio.save(cidadao);
			return "perfil-cidadao";
	    }
		
		return "home";
	}
	
	@PostMapping(MappingController.Cidadao.cadastro)
	public String salvarCidadao(@Valid @ModelAttribute Cidadao cidadao, Errors errors, SessionStatus sessionStatus) {
		if(errors.hasErrors()) {
			return "cidadao-form";
		}
		
		if (cidadao.getEndereco() != null) {
	        cidadao.getEndereco().setCidadao(cidadao);
	    }
		
		cidadaoRepositorio.save(cidadao);
		
		sessionStatus.setComplete();
		
		return "home";
	}
	
	@PostMapping(MappingController.Cidadao.delete + "/{id}")
	public String deleteCidadao(@PathVariable("id") Long id, Model model) {
		try {
			cidadaoRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {}	
		
		return "redirect:/cidadao/listar";
	}
}
