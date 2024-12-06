package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "politico")
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Obrigatório informar sigla do partido.")
	private String sigla;

	@NotBlank(message = "Obrigatório informar nome do partido.")
	private String nome;

	@NotBlank(message = "Obrigatório informar número do partido.")
	private String numero;

	@OneToMany(mappedBy = "partido")
	private List<Politico> politicos = new ArrayList<>();

	public void adicionarPolitico(Politico politico){
		politicos.add(politico);
	}
}