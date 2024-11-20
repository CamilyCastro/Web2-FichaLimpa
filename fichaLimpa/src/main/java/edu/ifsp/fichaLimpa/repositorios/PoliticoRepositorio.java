package edu.ifsp.fichaLimpa.repositorios;

import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.Politico;

public interface PoliticoRepositorio extends CrudRepository<Politico, Long>{
    Politico findByNome(String nome);
}
