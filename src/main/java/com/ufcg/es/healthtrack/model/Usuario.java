package com.ufcg.es.healthtrack.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    private String email;
    private String nome;
    private String senha;

    public Usuario(){}

    public Usuario(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean verificaSenha(String senha) {
        return this.senha.equals(senha);
    }
}
