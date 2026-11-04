package com.seduc2.escola.service;

import com.seduc2.escola.entity.Professor;
import com.seduc2.escola.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfessorService {

    private final ProfessorRepository repository;
    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> procurarProfessor(){
        return repository.findAll();
    }

    public void criarPerfil(Professor p){
        Professor professor = new Professor(p.getNome(), p.getIdade(), p.getCpf());
        repository.save(p);
    }
}
