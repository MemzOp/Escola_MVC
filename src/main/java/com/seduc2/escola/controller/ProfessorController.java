package com.seduc2.escola.controller;


import com.seduc2.escola.entity.Professor;
import com.seduc2.escola.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professores")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Professor> procurarProfessor(){
        return service.procurarProfessor();
    }

    @PostMapping
    public void criarPerfil(@RequestBody Professor p ){
        service.criarPerfil(p);
    }
}
