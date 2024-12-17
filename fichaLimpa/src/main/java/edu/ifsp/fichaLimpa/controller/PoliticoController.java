package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.ifsp.fichaLimpa.model.Publicacao;
import edu.ifsp.fichaLimpa.repositorios.PublicacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.ifsp.fichaLimpa.model.CategoriaEnum;
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
@RequestMapping(MappingController.Politico.MAIN)
@Slf4j
public class PoliticoController {
	
	@Autowired
	private PoliticoRepositorio politicoRepo; 
	
	@Autowired
	private PartidoRespositorio partidoRepo;
	
	@Autowired
	private PropostaRepositorio propostaRepo;

	@Autowired
	private PublicacaoRepositorio publicacaoRepo;

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
	public String listarPoliticos(Model model, @RequestParam(name = "query",required = false) String query) {
		List<Politico> politicos = new ArrayList<>();
	
		//buca politico 
		if(query != null && !query.isEmpty()) {
			politicos = politicoRepo.findPoliticoByNome(query);		
		}else {
			//busca todos se nao achar o politico 
			politicoRepo.findAll().forEach(politicos::add);
		}
		
		model.addAttribute("politicos", politicos);

		return "listar-politico";
	}
	
	@GetMapping(MappingController.Politico.edit + "/{id}")
	public String formEditarPolitico(@PathVariable("id") Long id, Model model) {
		Optional<Politico> opt = politicoRepo.findById(id);		
		
		if (opt.isPresent()) {
			
			Politico politico = opt.get();
	
			List<Partido> partidos = new ArrayList<>();
			partidoRepo.findAll().forEach(partidos::add);
			    
			model.addAttribute("partidos", partidos);
			model.addAttribute("politico", politico);
				
			return "editar-politico";
			   
		}		
		
		return MappingController.Politico.perfil + "/{id}";
	}
	
	@GetMapping(MappingController.Politico.perfil + "/{id}")
	public String perfilPolitico(@PathVariable("id") Long id, Model model){
		
		Optional<Politico> opt = politicoRepo.findById(id);		
		
		if (opt.isPresent()) {
			
			Politico politico = opt.get();
			
			List<Proposta> propostas = propostaRepo.findByPoliticoId(politico.getId());

			 Map<String, List<Proposta>> propostasPorCategoria = propostas.stream()
			            .collect(Collectors.groupingBy(p -> p.getCategoria().getDescricao()));

			List<Publicacao> publicacoes = publicacaoRepo.findByPoliticoId(politico.getId());
			
			model.addAttribute("publicacoes", publicacoes);
			model.addAttribute("politico", politico);
			model.addAttribute("propostasPorCategoria", propostasPorCategoria);
					
			return "perfil-politico";
		}
		
		return "home";
	}
	
	
	@PostMapping(MappingController.Politico.edit)
	public String editCidadao(@Valid @ModelAttribute Politico politico, Errors errors, Model model) {
		if (errors.hasErrors()){
			 List<Partido> partidos = new ArrayList<>();
			 
		        partidoRepo.findAll().forEach(partidos::add);
		        
		        model.addAttribute("politico", politico);
		        model.addAttribute("partidos", partidos); 			
			
			return "editar-politico";
		}
		
		Optional<Partido> opt = partidoRepo.findById(politico.getPartido().getId());		
		
		if (opt.isPresent()) {
			
			Partido partido = opt.get();		
			partido.adicionarPolitico(politico);
			politicoRepo.save(politico);
			
			//sessionStatus.setComplete();
			return "perfil-politico";

		}else
			return "/home";
	}
	
	
	@PostMapping(MappingController.Politico.delete + "/{id}")
	public String deletePolitico(@PathVariable("id") Long id, Model model) {
		try {
			politicoRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {}	
		
		return "listar-politico"; 
	}

	@PostMapping(MappingController.Politico.cadastro)
	public String executarCadastroPolitico(@Valid @ModelAttribute Politico politico, Errors errors, Model model, SessionStatus sessionStatus){
		
		if (errors.hasErrors()){
			 List<Partido> partidos = new ArrayList<>();
			 
		        partidoRepo.findAll().forEach(partidos::add);
		        
		        model.addAttribute("politico", politico);
		        model.addAttribute("partidos", partidos); 
		        
		        return "politico-form";
		}
		
		Optional<Partido> opt = partidoRepo.findById(politico.getPartido().getId());		
		
		if (opt.isPresent()) {
			
			Partido partido = opt.get();		
			partido.adicionarPolitico(politico);
			politicoRepo.save(politico);
			
			sessionStatus.setComplete();
			return "/home";
		}

		return "politico-form";
	}
}
