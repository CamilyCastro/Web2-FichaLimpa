package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.ifsp.fichaLimpa.model.Partido;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.model.Proposta;
import edu.ifsp.fichaLimpa.repositorios.PartidoRespositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PropostaRepositorio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@SessionAttributes("politico")
@RequestMapping()
@Slf4j
public class PoliticoController {
	
	@Autowired
	private PoliticoRepositorio politicoRepo; 
	
	@Autowired
	private PartidoRespositorio partidoRepo;
	
	@Autowired
	private PropostaRepositorio propostaRepo;

	@ModelAttribute(name = "politico")
	public Politico politico() {
		return new Politico();
	}
	
	@GetMapping("/politico")
	public String viewPolitico(Model model)  {
		List<Partido> partidos = new ArrayList<>();
	    partidoRepo.findAll().forEach(partidos::add);
	    
	    model.addAttribute("partidos", partidos);
		
		return "politico-form";
	}
	
	@GetMapping("/politico/listar")
	public String listarPoliticos(Model model) {
		List<Politico> politicos = new ArrayList<>();
		politicoRepo.findAll().forEach(politicos::add);
		
		model.addAttribute("politicos", politicos);
		log.info("Politicos: {}", politicos);
		return "listar-politico";
	}

	@GetMapping("/profile")
    public String findProposta(Politico politico){
    	//log.info(propostaRepo.findByIdPolitico(politico.getId()));
    	List<Proposta> propostas =  propostaRepo.findByPoliticoId(politico.getId());
    	
    	
    	return "perfil-politico";
    }

	@PostMapping("/politico")
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
