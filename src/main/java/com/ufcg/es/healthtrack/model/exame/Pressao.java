package com.ufcg.es.healthtrack.model.exame;

import com.ufcg.es.healthtrack.model.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

@Entity
public class Pressao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String observacoes;

    private int sistolica;

    private int diastolica;

    @ManyToOne
    private Usuario usuario;

    public Pressao(){}

    public Pressao(Usuario usuario, int sistolica, int diastolica, String observacoes) {
        this.usuario = usuario;
        this.sistolica = sistolica;
        this.diastolica = diastolica;
        this.observacoes = observacoes;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public int getSistolica(){
        return sistolica;
    }
    public int getDiastolica() {
        return diastolica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public long getId() {
        return id;
    }
}
