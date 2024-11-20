package edu.ifsp.fichaLimpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ifsp.fichaLimpa.model.Cidadao;
import edu.ifsp.fichaLimpa.model.Endereco;
import edu.ifsp.fichaLimpa.repositorios.CidadaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.EnderecoRepositorio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("cidadao")
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private CidadaoRepositorio cidadaoRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@ModelAttribute(name = "cidadao")
	public Cidadao cidadao() {
		return new Cidadao();
	}
	
	public Endereco endereco() {
		return new Endereco();
	}
	
	@GetMapping
	public String viewCidadao() {
		return "cidadao-form";
	}
	
	@PostMapping
	public String salvarCidadao(@Valid @ModelAttribute Cidadao cidadao, Errors errors) {
		if(errors.hasErrors()) {
			return "cidadao-form";
		}
		
		if (cidadao.getEndereco() != null) {
	        cidadao.getEndereco().setCidadao(cidadao);
	    }
//		enderecoRepositorio.save(endereco);
//		cidadao.setEndereco(endereco);
		cidadaoRepositorio.save(cidadao);
		
		return "perfil-cidadao";
	}
}
