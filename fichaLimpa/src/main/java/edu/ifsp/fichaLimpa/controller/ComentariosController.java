package edu.ifsp.fichaLimpa.controller;

import edu.ifsp.fichaLimpa.model.Comentarios;
import edu.ifsp.fichaLimpa.repositorios.ComentariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(MappingController.Comentarios.MAIN)
@SessionAttributes("comentarios")
public class ComentariosController {

    @Autowired
    private ComentariosRepositorio comentariosRepositorio;

    @ModelAttribute("comentarios")
    public Comentarios comentarios(){
        return new Comentarios();
    }


}
