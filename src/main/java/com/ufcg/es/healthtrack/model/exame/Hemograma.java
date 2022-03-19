package com.ufcg.es.healthtrack.model.exame;


import com.ufcg.es.healthtrack.model.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Hemograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private String descricao;
    private LocalDateTime date;
    private double hemoglobina;
    private double hemacrotito;
    private double hematimetria;
    private double reticulocitos;
    private double volumeCorpuscular;
    private double hemoglobinaCorpuscular;
    private double concentracaoHemoglobina;
    private double indiceAnisocitose;

    public Hemograma() {}


    public Hemograma(Usuario user, String descricao, LocalDateTime date, double hemoglobina, double hemacrotito, double hematimetria,
                     double reticulocitos, double volumeCorpuscular, double hemoglobinaCorpuscular, double concentracaoHemoglobina,
                     double indiceAnisocitose) {
        super();
        this.usuario = user;
        this.descricao = descricao;
        this.date = date;
        this.hemoglobina = hemoglobina;
        this.hemacrotito = hemacrotito;
        this.hematimetria = hematimetria;
        this.reticulocitos = reticulocitos;
        this.volumeCorpuscular = volumeCorpuscular;
        this.hemoglobinaCorpuscular = hemoglobinaCorpuscular;
        this.concentracaoHemoglobina = concentracaoHemoglobina;
        this.indiceAnisocitose = indiceAnisocitose;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public double getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(double hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public double getHemacrotito() {
        return hemacrotito;
    }

    public void setHemacrotito(double hemacrotito) {
        this.hemacrotito = hemacrotito;
    }

    public double getHematimetria() {
        return hematimetria;
    }

    public void setHematimetria(double hematimetria) {
        this.hematimetria = hematimetria;
    }

    public double getReticulocitos() {
        return reticulocitos;
    }

    public void setReticulocitos(double reticulocitos) {
        this.reticulocitos = reticulocitos;
    }

    public double getVolumeCorpuscular() {
        return volumeCorpuscular;
    }

    public void setVolumeCorpuscular(double volumeCorpuscular) {
        this.volumeCorpuscular = volumeCorpuscular;
    }

    public double getHemoglobinaCorpuscular() {
        return hemoglobinaCorpuscular;
    }

    public void setHemoglobinaCorpuscular(double hemoglobinaCorpuscular) {
        this.hemoglobinaCorpuscular = hemoglobinaCorpuscular;
    }

    public double getConcentracaoHemoglobina() {
        return concentracaoHemoglobina;
    }

    public void setConcentracaoHemoglobina(double concentracaoHemoglobina) {
        this.concentracaoHemoglobina = concentracaoHemoglobina;
    }

    public double getIndiceAnisocitose() {
        return indiceAnisocitose;
    }

    public void setIndiceAnisocitose(double indiceAnisocitose) {
        this.indiceAnisocitose = indiceAnisocitose;
    }

    public Long getId() {
        return id;
    }
}
