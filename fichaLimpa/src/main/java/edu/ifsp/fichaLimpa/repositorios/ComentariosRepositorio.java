package edu.ifsp.fichaLimpa.repositorios;

import edu.ifsp.fichaLimpa.model.Comentarios;
import edu.ifsp.fichaLimpa.model.Publicacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComentariosRepositorio extends CrudRepository<Comentarios, Long> {

    List<Comentarios> findByPublicacao(Publicacao publicacao);
}
