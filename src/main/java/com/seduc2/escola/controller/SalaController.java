package com.seduc2.escola.controller;

import com.seduc2.escola.entity.Sala;
import com.seduc2.escola.service.SalaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class SalaController {

    private final SalaService service;

    public SalaController(SalaService service) {
        this.service = service;
    }


    @GetMapping
    public List<Sala> mostrarSala(){
        return service.buscarTodos();
    }

    @PostMapping
    public void criarSala(@RequestBody Sala s){
        service.criarSala(s);

    }
}
