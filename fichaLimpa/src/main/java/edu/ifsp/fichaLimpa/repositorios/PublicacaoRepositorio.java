package edu.ifsp.fichaLimpa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.ifsp.fichaLimpa.model.Comentarios;
import edu.ifsp.fichaLimpa.model.Publicacao;

public interface PublicacaoRepositorio extends CrudRepository<Publicacao, Long> {
	
	@Query("SELECT p.avaliacao FROM Publicacao p WHERE p.politico.id = :id")
	List<Integer> findNotaByPolitico(@Param("id") Long id);
  
	List<Publicacao> findByPoliticoId(Long idPolitico);
	
	//@Query("SELECT * FROM Publicacao p WHERE p.aprovado = :false")
	List<Publicacao> findByAprovado(/*@Param("false") */boolean aprovado);
	
	List<Publicacao> findByDenunciar(String denunciar);

} 
