package edu.ifsp.fichaLimpa.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "politico")
public class Politico {
	
	@Id
	private long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cargo;
	
	@NotBlank
	private String nomeEleitoral;
	
	@NotBlank
	private int numeroEleitoral;
	@NotBlank
	
	private int registroCandidatura;
	@NotBlank
	
	private String dataNasc;
	@NotBlank
	
	private int contato;
	
	@OneToMany
	private Partido id_partido;
	
}
