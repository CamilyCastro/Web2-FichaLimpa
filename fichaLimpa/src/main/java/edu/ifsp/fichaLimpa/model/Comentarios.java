package edu.ifsp.fichaLimpa.model;

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

    @ManyToOne
    @JoinColumn(name = "id_publicacao")
    @ToString.Exclude
    private Publicacao publicacao;
}
