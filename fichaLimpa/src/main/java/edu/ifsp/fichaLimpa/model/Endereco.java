package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "cidadao")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Pattern(regexp = "\\d{5}-\\d{3}", message = "Informe um CEP no formato 00000-000")
	private String cep;
	
	@NotBlank(message = "Informe o nome da rua")
	private String logradouro;
	
	
	private int numero;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	@Size(min = 2, max = 2, message = "Informe a sigla do estado")
	private String estado;
	
	private String complemento;
	
	@OneToOne
	@JoinColumn(name = "cidadao", referencedColumnName = "id")
	private Cidadao cidadao;
}
