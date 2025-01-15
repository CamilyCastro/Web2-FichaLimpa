package edu.ifsp.fichaLimpa.controller;

import edu.ifsp.fichaLimpa.model.Cidadao;
import edu.ifsp.fichaLimpa.model.Comentarios;
import edu.ifsp.fichaLimpa.model.Publicacao;
import edu.ifsp.fichaLimpa.model.User;
import edu.ifsp.fichaLimpa.repositorios.ComentariosRepositorio;
import edu.ifsp.fichaLimpa.repositorios.PublicacaoRepositorio;
import edu.ifsp.fichaLimpa.repositorios.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(MappingController.Comentario.MAIN)
@SessionAttributes("comentario")
public class ComentariosController {

    @Autowired
    private ComentariosRepositorio comentariosRepositorio;

    @Autowired
    private PublicacaoRepositorio publicacaoRepositorio;

    @Autowired
    private UserRepositorio userRepo;

    @ModelAttribute("comentarios")
    public Comentarios comentarios() {
        return new Comentarios();
    }

    @GetMapping(MappingController.Comentario.listar)
    public String listarComentarios(Model model) {

        List<Comentarios> comentarios = new ArrayList<>();
        comentariosRepositorio.findAll().forEach(comentarios::add);

        model.addAttribute("comentarios", comentarios);
        return "perfil-publicacao";
    }

    @GetMapping(MappingController.Comentario.perfil + "/{id}")
    public String perfilComentario(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Comentarios> opt = comentariosRepositorio.findById(id);

        if (opt.isPresent()) {

            Comentarios comentario = opt.get();

            boolean isAdmin = userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
            if(userDetails.getUsername().equals(comentario.getCidadao().getUser().getUsername()) || isAdmin) {
                model.addAttribute("permissao", true);
            }

            model.addAttribute("comentario", comentario);

            return "perfil-comentario.html";
        }

        return "home";
    }

    @PostMapping(MappingController.Comentario.cadastro + "/{id}")
    public String salvarComentario(@PathVariable("id") Long id, @RequestParam("texto") String texto, Model model, @AuthenticationPrincipal UserDetails userDetails) {

        Optional<Publicacao> opt = publicacaoRepositorio.findById(id);

        if (opt.isPresent()) {

            Publicacao publicacao = opt.get();

            if(!texto.isBlank()) {

                Comentarios comentario = new Comentarios();

                Optional<User> optUser = userRepo.findById(userDetails.getUsername());
                if (optUser.isPresent()) {
                    User user = optUser.get();
                    Cidadao cidadao = user.getCidadao();
                    comentario.setCidadao(cidadao);
                }

                comentario.setTexto(texto);
                comentario.setPublicacao(publicacao);
                comentariosRepositorio.save(comentario);
            }
        }
        return "redirect:/publicacao/perfil/" + id;
    }

    @PostMapping(MappingController.Comentario.delete + "/{id}")
    public String deleteComentario(@PathVariable("id") Long id) {
        comentariosRepositorio.deleteById(id);
        return "home";
    }


}
