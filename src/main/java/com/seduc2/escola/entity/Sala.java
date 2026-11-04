package com.seduc2.escola.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cadeiras;
    private int mesas;
    private int alunos;

    public Sala() {
    }

    public Sala(int id, String nome, String cadeiras, int mesas, int alunos) {
        this.id = id;
        this.nome = nome;
        this.cadeiras = cadeiras;
        this.mesas = mesas;
        this.alunos = alunos;
    }

    public Sala(String nome, String cadeiras, int mesas, int alunos) {
        this.nome = nome;
        this.cadeiras = cadeiras;
        this.mesas = mesas;
        this.alunos = alunos;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(String cadeiras) {
        this.cadeiras = cadeiras;
    }

    public int getMesas() {
        return mesas;
    }

    public void setMesas(int mesas) {
        this.mesas = mesas;
    }

    public int getAlunos() {
        return alunos;
    }

    public void setAlunos(int alunos) {
        this.alunos = alunos;
    }
}
