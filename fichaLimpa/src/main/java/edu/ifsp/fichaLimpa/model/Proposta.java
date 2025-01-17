package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@ToString
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank()
	private String titulo;
	
	@NotBlank
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_categoria") private Categoria categoria;
	 */

	@ManyToOne
	@JoinColumn(name = "id_politico")
	@JsonBackReference
	private Politico politico;

	/*
	 * @OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL) private
	 * List<Publicacao> listarpublicacao = new ArrayList<>();
	 */
	 
}
