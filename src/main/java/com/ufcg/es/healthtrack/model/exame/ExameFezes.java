package com.ufcg.es.healthtrack.model.exame;

import com.ufcg.es.healthtrack.model.Usuario;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class ExameFezes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    private String descricao;
    private LocalDateTime date;
    private String aspectoGeral;
    private String protozoarios;
    private String helmintos;
    private String metodo;
    private String observacoes;

    public ExameFezes() {}


    public ExameFezes(Usuario user, String descricao, LocalDateTime date, String aspectoGeral, String protozoarios, String helmintos,
                      String metodo, String observacoes) {
        super();
        this.usuario = user;
        this.descricao = descricao;
        this.date = date;
        this.aspectoGeral = aspectoGeral;
        this.protozoarios = protozoarios;
        this.helmintos = helmintos;
        this.metodo = metodo;
        this.observacoes = observacoes;
    }

    public ExameFezes(Long id, Usuario user, String descricao, LocalDateTime date, String aspectoGeral, String protozoarios, String helmintos,
                      String metodo, String observacoes) {
        super();
        this.id = id;
        this.usuario = user;
        this.descricao = descricao;
        this.date = date;
        this.aspectoGeral = aspectoGeral;
        this.protozoarios = protozoarios;
        this.helmintos = helmintos;
        this.metodo = metodo;
        this.observacoes = observacoes;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario user) {
        this.usuario = user;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAspectoGeral() {
        return aspectoGeral;
    }

    public void setAspectoGeral(String aspectoGeral) {
        this.aspectoGeral = aspectoGeral;
    }

    public String getProtozoarios() {
        return protozoarios;
    }

    public void setProtozoarios(String protozoarios) {
        this.protozoarios = protozoarios;
    }

    public String getHelmintos() {
        return helmintos;
    }

    public void setHelmintos(String helmintos) {
        this.helmintos = helmintos;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

