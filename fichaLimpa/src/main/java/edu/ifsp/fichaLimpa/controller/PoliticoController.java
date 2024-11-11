package edu.ifsp.fichaLimpa.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import edu.ifsp.fichaLimpa.model.Partido;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.repositorios.PartidoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;


@Controller
@SessionAttributes("politico")
@RequestMapping("/politico")
public class PoliticoController {
	
	@Autowired
	private PoliticoRepositorio politicoRepo; 
	
	@Autowired
	private PartidoRepositorio partidoRepo;

	@ModelAttribute(name = "politico")
	public Politico politico() {
		//criar objeto de politico para usar na sessao web
		return new Politico();
	}
	
	@GetMapping
	public String viewPolitico() {
		return "politico-form";
	}

	@PostMapping
	public String executarCadastroPolitico(@Valid Politico politico, @RequestParam("idPartido") Long idPartido ,
			Errors errors){
		
		if (errors.hasErrors()){
			return "politico-form";
		}
		
		Partido partido = partidoRepo.findById(idPartido).orElse(null);
		
		if(partido == null) {
			return "politico-form";
		}

		politico.setPartido(partido);
		politicoRepo.save(politico);

		return "/home";
	}

}
