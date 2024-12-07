package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String descricao;
	
	@OneToMany(mappedBy = "categoria")
	private List<Proposta> listaPropostas = new ArrayList<>();
	
	public void adicionarProposta(Proposta proposta) {
		listaPropostas.add(proposta);
	}
}
