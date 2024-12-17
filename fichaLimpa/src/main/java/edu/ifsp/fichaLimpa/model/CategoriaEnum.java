package edu.ifsp.fichaLimpa.model;

import lombok.Getter;

public enum CategoriaEnum {

	SAUDE("Saúde"),
	TECNOLOGIA("Tecnologia"),
	ECONOMIA("Economia"),
	SOCIAL("Social"),
	SEGURANCA("Segurança");

	@Getter
	private final String descricao;

	CategoriaEnum(String descricao){
		this.descricao = descricao;
	}

}