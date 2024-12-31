package edu.ifsp.fichaLimpa.repositorios;

import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.User;

public interface UserRepositorio extends CrudRepository<User, String> {

}
