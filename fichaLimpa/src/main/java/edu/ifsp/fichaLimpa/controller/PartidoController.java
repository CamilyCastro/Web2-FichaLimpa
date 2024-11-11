package edu.ifsp.fichaLimpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ifsp.fichaLimpa.model.Partido;
import edu.ifsp.fichaLimpa.repositorios.PartidoRepositorio;

@Controller
@RequestMapping("/partido")
public class PartidoController {
	
	@Autowired
	private PartidoRepositorio partidoRepo;
	
	@ModelAttribute(name = "partido")
	public Partido partido() {
		return new Partido();
	}
	
	public String viewPartido() {
		return "partido-form";
	}
}
