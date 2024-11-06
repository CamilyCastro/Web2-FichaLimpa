package edu.ifsp.fichaLimpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@SessionAttributes("politico")
@RequestMapping("/politico")
public class PoliticoController {
	
	@Autowired
	private PoliticoRepositorio politicoRepo; 

	@ModelAttribute(name = "politico")
	public Politico politico() {
		//criar objeto de politico para usar na sessao web
		return new Politico();
	}
	
	@GetMapping("/cadastroPolitico")
	public String politicoForm() {
		return "politico-form";
	}
	
}
