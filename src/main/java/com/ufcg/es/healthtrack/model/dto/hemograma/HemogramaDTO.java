package com.ufcg.es.healthtrack.model.dto.hemograma;

import java.time.LocalDateTime;

public class HemogramaDTO {

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

    public HemogramaDTO(String descricao, LocalDateTime date, double hemoglobina, double hemacrotito, double hematimetria, double reticulocitos, double volumeCorpuscular, double hemoglobinaCorpuscular, double concentracaoHemoglobina, double indiceAnisocitose) {
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

    public HemogramaDTO() {
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
}
