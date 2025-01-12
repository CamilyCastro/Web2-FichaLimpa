package edu.ifsp.fichaLimpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "publicacao")
    private List<Comentarios> comentarios = new ArrayList<>();

    public void adicionarComentarios(Comentarios coment){
        comentarios.add(coment);
    }

}
