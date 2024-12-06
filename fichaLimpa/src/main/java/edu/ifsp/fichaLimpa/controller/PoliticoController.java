package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

import edu.ifsp.fichaLimpa.model.Partido;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.model.Proposta;
import edu.ifsp.fichaLimpa.repositorios.PartidoRespositorio;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PropostaRepositorio;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes("politico")
@RequestMapping(MappingController.Politico.MAIN)
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
	
	@GetMapping(MappingController.Politico.cadastro)
	public String cadastrarPolitico(Model model)  {
		List<Partido> partidos = new ArrayList<>();
	    partidoRepo.findAll().forEach(partidos::add);
	    
	    Politico politico = new Politico();
	    
		model.addAttribute("politico", politico);
	    model.addAttribute("partidos", partidos);
		
		return "politico-form";
	}
	

	@GetMapping(MappingController.Politico.listar)
	public String listarPoliticos(Model model) {
		List<Politico> politicos = new ArrayList<>();
		politicoRepo.findAll().forEach(politicos::add);
		
		model.addAttribute("politicos", politicos);

		return "listar-politico";
	}	
	
	@GetMapping(MappingController.Politico.perfil + "/{id}")
	public String perfilPolitico(@PathVariable("id") Long id, Model model){
		
		Optional<Politico> opt = politicoRepo.findById(id);		
		
		if (opt.isPresent()) {
			
			Politico politico = opt.get();
			
			List<Proposta> propostas = propostaRepo.findByPoliticoId(politico.getId());
			
			 Map<String, List<Proposta>> propostasPorCategoria = propostas.stream()
			            .collect(Collectors.groupingBy(p -> p.getCategoria().getDescricao()));
			
			model.addAttribute("politico", politico);
			model.addAttribute("propostasPorCategoria", propostasPorCategoria);
					
			return "perfil-politico";
		}
		
		return "home";
	}
	
	@PostMapping(MappingController.Politico.delete + "/{id}")
	public String deletePolitico(@PathVariable("id") Long id, Model model) {
		try {
			politicoRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {}	
		
		return "listar-politico"; 
	}

	@PostMapping()
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

			return "/home";
		}else
			return "politico/form";
	}
}
