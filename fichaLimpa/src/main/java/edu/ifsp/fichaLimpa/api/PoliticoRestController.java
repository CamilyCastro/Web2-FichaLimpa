package edu.ifsp.fichaLimpa.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifsp.fichaLimpa.controller.MappingController;
import edu.ifsp.fichaLimpa.model.Politico;
import edu.ifsp.fichaLimpa.repositorios.PoliticoRepositorio;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/api/politico", produces="application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class PoliticoRestController {
	
	private final PoliticoRepositorio politicoRepo;
	
	public PoliticoRestController(PoliticoRepositorio politicoRepo) {
		this.politicoRepo = politicoRepo;
	}
	
	@GetMapping("/todos")
	public Iterable<Politico> listagemPoliticos(){
		return politicoRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Politico> perfilPolitico(@PathVariable("id") Long id){
		
		Optional<Politico> opt = politicoRepo.findById(id);
		
		if (opt.isPresent()) {
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/ranking")
	public List<Politico> getRanking() {
		 return politicoRepo.findAllOrderByNota();
	}
}
