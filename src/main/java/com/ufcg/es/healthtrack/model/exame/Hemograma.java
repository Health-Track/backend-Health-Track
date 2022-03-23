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
public class Hemograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getHemoglobina() {
        return hemoglobina;
    }

    public double getHemacrotito() {
        return hemacrotito;
    }

    public double getHematimetria() {
        return hematimetria;
    }

    public double getReticulocitos() {
        return reticulocitos;
    }

    public double getVolumeCorpuscular() {
        return volumeCorpuscular;
    }

    public double getHemoglobinaCorpuscular() {
        return hemoglobinaCorpuscular;
    }

    public double getConcentracaoHemoglobina() {
        return concentracaoHemoglobina;
    }

    public double getIndiceAnisocitose() {
        return indiceAnisocitose;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
