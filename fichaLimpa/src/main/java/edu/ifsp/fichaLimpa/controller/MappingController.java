package edu.ifsp.fichaLimpa.controller;

public interface MappingController {
	
	interface Cidadao {
		String MAIN = "/cidadao";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
		String listar = "/listar";
	}
	
	interface Politico{
		String MAIN = "/politico";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
		String listar = "/listar";
		String rankig = "/ranking";
	}
	
	interface Partido{
		String MAIN = "/partido";
	}
	interface Proposta{
		String MAIN = "/proposta";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
		String listar = "/listar";
	}

	interface Publicacao{
		String MAIN = "/publicacao";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
		String listar = "/listar";
		String aprovar = "/aprovar";
	}
}
