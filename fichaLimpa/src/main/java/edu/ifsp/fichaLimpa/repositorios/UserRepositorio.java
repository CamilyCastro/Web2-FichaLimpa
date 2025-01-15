package edu.ifsp.fichaLimpa.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.ifsp.fichaLimpa.model.User;

public interface UserRepositorio extends CrudRepository<User, String> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(String username);
}
