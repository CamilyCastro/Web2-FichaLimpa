package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Politico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;

	@NotBlank(message = "Obrigatório informar o nome.")
	private String nome;
	
	private Double nota;

	@NotBlank(message = "Obrigatório informar o cargo.")
	private String cargo;

	@NotBlank(message = "Obrigatório informar o nome eleitoral.")
	private String nomeEleitoral;

	@NotBlank(message = "Obrigatório informar o numero eleitoral.")
	private String numeroEleitoral;

	@NotBlank(message = "Obrigatório informar o registro.")
	private String registroCandidatura;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	@NotBlank(message = "Obrigatório informar o contato.")
	private String contato;

	@ManyToOne
	@JoinColumn(name = "id_partido")
	@NotNull(message = "Selecione um partido.")
	@ToString.Exclude
	private Partido partido;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "politico")
	private List<Proposta> listaPropostas = new ArrayList<>();
	
	public void adicionarProposta(Proposta proposta) {
		listaPropostas.add(proposta);
	}
	
	@ToString.Exclude
	@OneToMany(mappedBy = "politico")
	private List<Publicacao> publicacoes = new ArrayList<>();

	public void adicionarPublicacoes(Publicacao publicacao){
		publicacoes.add(publicacao);
	}
}
