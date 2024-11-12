package edu.ifsp.fichaLimpa.repositorios;

import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.Partido;

public interface PartidoRespositorio extends CrudRepository<Partido, Long>{
    Partido findByNome(String nome);
}
