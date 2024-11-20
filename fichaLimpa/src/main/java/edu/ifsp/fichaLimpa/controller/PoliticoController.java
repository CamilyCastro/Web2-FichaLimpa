package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ifsp.fichaLimpa.model.Partido;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.repositorios.PartidoRespositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import jakarta.validation.Valid;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes("politico")
@RequestMapping("/politico")
public class PoliticoController {
	
	@Autowired
	private PoliticoRepositorio politicoRepo; 
	
	@Autowired
	private PartidoRespositorio partidoRepo;

	@ModelAttribute(name = "politico")
	public Politico politico() {
		return new Politico();
	}
	
	@GetMapping
	public String viewPolitico(Model model)  {
		List<Partido> partidos = new ArrayList<>();
	    partidoRepo.findAll().forEach(partidos::add);
	    
	    model.addAttribute("partidos", partidos);
		
		return "politico-form";
	}

	@PostMapping
	public String executarCadastroPolitico(@Valid Politico politico, SessionStatus sessionStatus, Errors errors){
		
		if (errors.hasErrors()){
			return "politico-form";
		}

		Partido partido = new Partido();

		if(politico.getPartido() != null){
			partido.adicionarPolitico(politico);
			politicoRepo.save(politico);

			//precisa finalizar a sessao para nao dar erro na linha: <select id="partido" th:field="*{partido}">
			//esse erro impedia de criar novos politicos
			sessionStatus.setComplete();

			return "perfil-politico";
		}else
			return "/home";
	}

}
