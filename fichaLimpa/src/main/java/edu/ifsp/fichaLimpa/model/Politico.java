package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@NotBlank(message = "Obrigatório informar o nome.")
	private String nome;

	@NotBlank(message = "Obrigatório informar o cargo.")
	private String cargo;

	@NotBlank(message = "Obrigatório informar o nome eleitoral.")
	private String nomeEleitoral;

	@NotBlank(message = "Obrigatório informar o numero eleitoral.")
	private int numeroEleitoral;

	@NotBlank(message = "Obrigatório informar o registro.")
	private int registroCandidatura;

	@NotBlank(message = "Obrigatório informar a data de nascimento.")
	private String dataNasc;
	
	@NotBlank(message = "Obrigatório informar o contato.")
	private int contato;

	@ManyToOne
	@JoinColumn(name = "id_partido")
	private Partido partido;

}
