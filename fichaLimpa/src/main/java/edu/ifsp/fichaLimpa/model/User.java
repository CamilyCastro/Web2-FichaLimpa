package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "users")
@ToString(exclude = "cidadao")
public class User {
	@Id
	@NotBlank(message = "Obrigatório informar um username.")
	private String username;
	
	@NotBlank(message = "Obrigatório informar uma senha.")
	@Size(min = 6, message = "Informe uma senha com no mínimo 6 dígitos")
	private String password;
	
	@OneToOne
	@JoinColumn(name = "cidadao_id", referencedColumnName = "id")
	private Cidadao cidadao;
	
	private boolean enabled;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authorities> authorities = new ArrayList<>();
	
	public void adicionarAuthority(Authorities authority){
		authorities.add(authority);
	}
}
