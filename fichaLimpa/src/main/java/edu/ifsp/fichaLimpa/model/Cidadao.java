package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cidadao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe um nome")
	private String nome;
	
	@NotBlank
	@Pattern(regexp = "\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "Informe um CPF no formato 000.000.000-00")
	private String cpf;
	
	@NotBlank
	@Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}", message="Informe um telefone no formato (00)00000-0000")
	private String telefone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	@OneToOne(mappedBy = "cidadao", cascade = CascadeType.ALL)
	@Valid
	private Endereco endereco;

	@OneToMany(mappedBy = "cidadao", cascade = CascadeType.ALL)
	private List<Publicacao> publicacoes = new ArrayList<>();
	
	public void adicionarPubli(Publicacao publicacao) {
		publicacoes.add(publicacao);
	}
	
	@OneToOne(mappedBy = "cidadao", cascade = CascadeType.ALL)
	@Valid
	private User user; 
	
	
	public void setUser(User user) {
		user.setCidadao(this);	
		user.setEnabled(true);
		this.user = user;
	}
	
}
