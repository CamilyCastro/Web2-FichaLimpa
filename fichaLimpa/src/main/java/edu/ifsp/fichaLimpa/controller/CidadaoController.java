package edu.ifsp.fichaLimpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.ifsp.fichaLimpa.model.Authorities;
import edu.ifsp.fichaLimpa.model.Cidadao;
import edu.ifsp.fichaLimpa.model.Endereco;
import edu.ifsp.fichaLimpa.model.User;
import edu.ifsp.fichaLimpa.repositorios.CidadaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.UserRepositorio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("cidadao")
@RequestMapping(MappingController.Cidadao.MAIN)
public class CidadaoController {

	@Autowired
	private CidadaoRepositorio cidadaoRepositorio;
	
	@Autowired
	private UserRepositorio userRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@ModelAttribute(name = "cidadao")
	public Cidadao cidadao() {
		return new Cidadao();
	}
	
	public Endereco endereco() {
		return new Endereco();
	}
	
	@GetMapping(MappingController.Cidadao.cadastro)
	public String saveCidadao(Model model) {
		Cidadao cidadao = new Cidadao();
		model.addAttribute("cidadao", cidadao);
		return "cidadao-form";
	}
	
	@GetMapping(MappingController.Cidadao.listar)
	public String listarCidadao(Model model) {
		List<Cidadao> cidadaos = new ArrayList<>();
		cidadaoRepositorio.findAll().forEach(cidadaos::add);
		
		model.addAttribute("cidadaos", cidadaos);
		return "listar-cidadao";
	}
	
	@GetMapping(MappingController.Cidadao.edit + "/{id}")
	public String formEditarCidadao(@PathVariable("id") Long id, Model model) {
		Optional<Cidadao> opt = cidadaoRepositorio.findById(id);		
		
		if (opt.isPresent()) {
			
			Cidadao cidadao = opt.get();
			model.addAttribute("cidadao", cidadao);
		}
		
		return "editar-cidadao";
	}
	
	@GetMapping(MappingController.Cidadao.perfil + "/{id}")
	public String perfilCidadao(@PathVariable("id") Long id, Model model){
		Optional<Cidadao> opt = cidadaoRepositorio.findById(id);		
		
		if (opt.isPresent()) {
			
			Cidadao cidadao = opt.get();
			model.addAttribute("cidadao", cidadao);			
		}
		
		return "perfil-cidadao";
	}
	
	@GetMapping(MappingController.Cidadao.perfil)
	public String perfilCidadao( Model model,  @AuthenticationPrincipal UserDetails userDetails){
		
		if(userDetails.getUsername() != null) {
			
			Cidadao cidadao = cidadaoRepositorio.findByUserUsername(userDetails.getUsername());	
			model.addAttribute("cidadao", cidadao);
		}
		
		return "perfil-cidadao";
	}
	
	@GetMapping(MappingController.Cidadao.editSenha)
	public String editSenha(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
		
		if(userDetails.getUsername() != null) {
			
			User user = userRepo.findByUsername(userDetails.getUsername());	
			model.addAttribute("user", user);
		}		
		
		return "alterar-senha";
	}
	
	@PostMapping(MappingController.Cidadao.edit)
	public String editCidadao(@Valid @ModelAttribute Cidadao cidadao, Errors errors ) {
		if(errors.hasErrors()) {
			return "editar-cidadao";
		}
		
		if (cidadao.getEndereco() != null) {
	        cidadao.getEndereco().setCidadao(cidadao);
	        cidadaoRepositorio.save(cidadao);
			
	    }
		
		return "perfil-cidadao";
	}
	
	@PostMapping(MappingController.Cidadao.editSenha)
	public String editSenha(@Valid @ModelAttribute User user, 
	                        @RequestParam("novaSenha") String novaSenha,
	                        @RequestParam("confirmacao") String confirmacao,
	                        Model model, Errors errors) {
	    
	   if (errors.hasErrors()) {
	        return "alterar-senha";
	    }

	    User usuarioBanco = userRepo.findById(user.getUsername()).orElse(null);
	    if (usuarioBanco == null) {
	        model.addAttribute("erroSenhaAtual", "Usuário não encontrado");
	        return "alterar-senha";
	    }

	    if (!bCryptPasswordEncoder.matches(user.getPassword(), usuarioBanco.getPassword())) {
	        model.addAttribute("erroSenhaAtual", "Senha incorreta");
	        return "alterar-senha";
	    }


	    if (novaSenha.length() < 6) {
	        model.addAttribute("erroSenhaNova", "A nova senha deve conter no mínimo 6 caracteres");
	        return "alterar-senha";
	    }

	    if (!novaSenha.equals(confirmacao)) {
	        model.addAttribute("erroConfirmacao", "A nova senha e a confirmação não coincidem");
	        return "alterar-senha";
	    }
	    
	    String encryptedNovaSenha = bCryptPasswordEncoder.encode(novaSenha);

	    usuarioBanco.setPassword(encryptedNovaSenha);
	    userRepo.save(usuarioBanco);

	    return "/home";
	}

	
	@PostMapping(MappingController.Cidadao.cadastro)
	public String salvarCidadao(@Valid @ModelAttribute Cidadao cidadao, Errors errors, SessionStatus sessionStatus, Model model) {
		if(errors.hasErrors()) {
			return "cidadao-form";
		}
		
		if (cidadao.getEndereco() != null) {
	        cidadao.getEndereco().setCidadao(cidadao);
	    }
		
		Cidadao existente = cidadaoRepositorio.findByCpf(cidadao.getCpf());
		
		if (existente != null) {
	        model.addAttribute("erro", "CPF já cadastrado. Por favor, tente outro.");
	        return "cidadao-form";
	    }
		
		/*User emUso = userRepo.findByUsername(cidadao.getUser().getUsername());
		
		if(emUso != null) {
			model.addAttribute("erro", "Email já cadastrado. Por favor, tente outro.");
	        return "cidadao-form";
		}*/
		
		cidadaoRepositorio.save(cidadao);
		System.out.println(cidadao.getUser());
		if (cidadao.getUser() != null) {
			
	        String encryptedPassword = bCryptPasswordEncoder.encode(cidadao.getUser().getPassword());
			cidadao.getUser().setCidadao(cidadao);
			cidadao.getUser().setPassword(encryptedPassword);
			
			Authorities auth = new Authorities();
			auth.setAuthority("ROLE_USER");
			auth.setUser(cidadao.getUser());
			
			cidadao.getUser().adicionarAuthority(auth);
			
			userRepo.save(cidadao.getUser());
		}
				
		sessionStatus.setComplete();
		
		return "login";
	}
	
	@PostMapping(MappingController.Cidadao.delete + "/{id}")
	public String deleteCidadao(@PathVariable("id") Long id, Model model) {
		try {
			cidadaoRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {}	
		
		return "redirect:/cidadao/listar";
	}
}
