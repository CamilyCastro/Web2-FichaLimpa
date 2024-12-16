package edu.ifsp.fichaLimpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.Politico;

public interface PoliticoRepositorio extends CrudRepository<Politico, Long>{
	
    @Query("select p from Politico p where p.nome like %?1%")
	List<Politico> findPoliticoByNome(String nome);
}
