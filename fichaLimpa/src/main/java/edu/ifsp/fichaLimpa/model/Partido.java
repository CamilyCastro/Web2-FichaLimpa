package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity

public class Partido {

	@Id
	private long id;

	@NotBlank(message = "Obrigatório informar sigla do partido.")
	private String sigla;

	@NotBlank(message = "Obrigatório informar nome do partido.")
	private String nome;

	@NotBlank(message = "Obrigatório informar número do partido.")
	private String numero;

	@OneToMany(mappedBy = "partido")
	private List<Politico> politico = new ArrayList<>();
}