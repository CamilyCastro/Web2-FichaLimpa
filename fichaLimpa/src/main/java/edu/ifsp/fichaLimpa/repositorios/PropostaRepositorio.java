package edu.ifsp.fichaLimpa.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.Proposta;

public interface PropostaRepositorio extends CrudRepository<Proposta, Long>{
	List<Proposta> findByPoliticoId(Long idPolitico);	
}
