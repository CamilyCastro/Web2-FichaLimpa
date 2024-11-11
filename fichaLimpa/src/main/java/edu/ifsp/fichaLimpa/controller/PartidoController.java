package edu.ifsp.fichaLimpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ifsp.fichaLimpa.model.Partido;
import edu.ifsp.fichaLimpa.repositorios.PartidoRespositorio;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("politico")
@RequestMapping("/partido")
public class PartidoController {
	
	@Autowired
	private PartidoRespositorio partidoRepo;
	
	@ModelAttribute(name = "partido")
	public Partido partido() {
		return new Partido();
	}
	
	@GetMapping
	public String viewPartido() {
		return "partido-form";
	}
	
	@PostMapping
	public String cadastroPartido(@Valid Partido partido, Errors errors){
		if (errors.hasErrors()){
			return "partido-form";
		}
		
		partidoRepo.save(partido);
		
		return("redirect:/");
	}
}
