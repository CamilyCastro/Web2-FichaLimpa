package edu.ifsp.fichaLimpa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity

public class Partido {

@Id
long id;

@NotBlank(message = "Obrigatório informar sigla do partido.")
String sigla;

@NotBlank(message = "Obrigatório informar nome do partido.")
String nome;

@NotBlank(message = "Obrigatório informar número do partido.")
String numero;


@ManyToOne
@JoinColumn(name = "id_partido")
List politicos = new ArrayList();
//ettetetete

}