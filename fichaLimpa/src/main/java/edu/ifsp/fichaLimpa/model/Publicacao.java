package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@ToString
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //AUTOMATICO
    private LocalDateTime data_publicacao;

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

	/*@NotNull 
	private Integer resposta;//coloquei Integer pq ele permite valores nulos e nao coloca um 0 no campo de texto*/
	 
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_cidadao")
    private Cidadao cidadao;

    @ManyToOne
	@JoinColumn(name = "id_politico")
	private Politico politico;

}
