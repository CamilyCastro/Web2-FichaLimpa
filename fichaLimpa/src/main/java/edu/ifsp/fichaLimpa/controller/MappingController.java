package edu.ifsp.fichaLimpa.controller;

public interface MappingController {
	
	interface Cidadao {
		String MAIN = "/cidadao";
		String cadastro = "/cidadao/cadastro";
		String delete = "/cidadao/delete";
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
