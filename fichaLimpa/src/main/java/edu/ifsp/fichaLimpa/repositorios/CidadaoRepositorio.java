package edu.ifsp.fichaLimpa.repositorios;

import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.Cidadao;

public interface CidadaoRepositorio extends CrudRepository<Cidadao, Long> {

	Cidadao findByUserUsername(String username);
	Cidadao findByCpf(String cpf);
}
