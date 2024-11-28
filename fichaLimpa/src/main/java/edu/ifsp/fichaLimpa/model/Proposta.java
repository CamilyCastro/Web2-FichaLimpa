package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "proposta")
@RequiredArgsConstructor
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank()
	private String titulo;
	
	@NotBlank
	private String descricao;
	
	private int nota;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_politico")
	private Politico politico;
	 
}
