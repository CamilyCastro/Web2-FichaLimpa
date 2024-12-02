package edu.ifsp.fichaLimpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.ifsp.fichaLimpa.model.Proposta;

public interface PropostaRepositorio extends CrudRepository<Proposta, Long>{

	List<Proposta> findByPoliticoId(Long idPolitico);	

	@Query("SELECT p FROM Proposta p WHERE p.politico.id = :politicoId ORDER BY p.categoria.descricao, p.titulo")
	List<Proposta> findAllByPoliticoIdOrderByCategoriaAndTitulo(@Param("politicoId") Long politicoId);

}
