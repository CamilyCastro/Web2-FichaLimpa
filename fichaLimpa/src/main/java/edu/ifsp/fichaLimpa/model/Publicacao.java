package edu.ifsp.fichaLimpa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //AUTOMATICO
    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    //MANUAL
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_publicacao;*/

    @NotBlank(message = "Prenchimento obrigatório.")
    private String titulo;

    @NotBlank(message = "Prenchimento obrigatório.")
    private String descricao;

    //Procrurar um tipo arquivos
    private String anexo;
    
    @NotNull(message = "Prenchimento obrigatório.")
    private int avaliacao;
    
    private boolean aprovado;

	/*@NotNull 
	private Integer resposta;//coloquei Integer pq ele permite valores nulos e nao coloca um 0 no campo de texto*/
	 
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_cidadao")
    private Cidadao cidadao;

    @ToString.Exclude
    @ManyToOne
	@JoinColumn(name = "id_politico")
	private Politico politico;

}
