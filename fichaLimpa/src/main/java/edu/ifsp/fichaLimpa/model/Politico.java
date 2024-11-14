package edu.ifsp.fichaLimpa.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "politico")
@RequiredArgsConstructor
public class Politico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
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
	@Pattern(regexp = "\\d{2}/(0[1-9]|1[0-2])/\\d{4}", message = "Data de nascimento deve estar no formato DD/MM/AAAA")
	@Column(name = "data_nascimento")
	private Date dataNasc;
	
	@NotBlank(message = "Obrigatório informar o contato.")
	private String contato;

	@ManyToOne
	@JoinColumn(name = "id_partido")
	private Partido partido;

}
