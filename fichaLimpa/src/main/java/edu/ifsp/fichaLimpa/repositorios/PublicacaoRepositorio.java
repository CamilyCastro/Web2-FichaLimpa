package edu.ifsp.fichaLimpa.repositorios;

import edu.ifsp.fichaLimpa.model.Publicacao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface PublicacaoRepositorio extends CrudRepository<Publicacao, Long> {
	List<Publicacao> findByPoliticoId(Long idPolitico);
}
