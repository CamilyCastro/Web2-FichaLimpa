package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "authorities")
@ToString(exclude = "cidadao")
public class Authorities {
	
	@Id
	@ManyToOne
    @JoinColumn(name = "username")
    private User user;
	
	@NotBlank
	private String authority;
}
