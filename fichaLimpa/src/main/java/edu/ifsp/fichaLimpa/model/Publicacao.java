package edu.ifsp.fichaLimpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@ToString
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_publicacao;

    @NotBlank
    private String titulo;

    private String descricao;

    //Procrurar um tipo arquivos
    private String anexo;

    @NotNull
    private Integer resposta;//coloquei Integer pq ele permite valores nulos e nao coloca um 0 no campo de texto

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cidadao")
    private Cidadao cidadao;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_proposta")
    private Proposta proposta;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_politico")
    private Politico politico;

}
