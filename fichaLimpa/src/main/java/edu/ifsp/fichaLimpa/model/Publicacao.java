package edu.ifsp.fichaLimpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_cidadao")
    @JsonBackReference
    private Cidadao cidadao;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_politico")
    @JsonBackReference
    private Politico politico;

    @ToString.Exclude
    @OneToMany(mappedBy = "publicacao")
    @JsonManagedReference
    private List<Comentarios> comentarios = new ArrayList<>();

    public void adicionarComentarios(Comentarios coment){
        comentarios.add(coment);
    }

}
