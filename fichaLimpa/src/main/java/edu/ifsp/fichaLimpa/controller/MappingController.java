package edu.ifsp.fichaLimpa.controller;

public interface MappingController {
	
	interface Cidadao {
		String MAIN = "/cidadao";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
		String listar = "/listar";
		String editSenha = "/editSenha";
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
		String coment = "/coment";
		String aprovarDenuncia = "/aprovarDenuncia";
		String denunciar = "/denunciar";	
	}

	interface Comentario{
		String MAIN = "/comentario";
		String cadastro = "/cadastro";
		String delete = "/delete";
		String edit  = "/edit";
		String perfil  = "/perfil";
		String listar = "/listar";
		String aprovar = "/aprovar";
		String denunciar  = "/denunciar";
		String editDenuncia = "/editDenuncia";
	}
}
