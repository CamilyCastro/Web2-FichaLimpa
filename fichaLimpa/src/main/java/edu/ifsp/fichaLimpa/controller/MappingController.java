package edu.ifsp.fichaLimpa.controller;

public interface MappingController {
	
	interface Cidadao {
		String MAIN = "/cidadao";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
	}
	
	interface Politico{
		String MAIN = "/politico";
	}
	
	interface Partido{
		String MAIN = "/partido";
	}
	interface Proposta{
		String MAIN = "/proposta";
	}
}
