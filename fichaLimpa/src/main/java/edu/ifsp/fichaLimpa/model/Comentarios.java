package edu.ifsp.fichaLimpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String texto;
    
    private String denunciar;

    @ManyToOne
    @JoinColumn(name = "id_publicacao")
    @ToString.Exclude
    @JsonBackReference
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "id_cidadao")
    @ToString.Exclude
    @JsonBackReference
    private Cidadao cidadao;
}
