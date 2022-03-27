package com.ufcg.es.healthtrack.model.dto.hemograma;

import java.time.LocalDateTime;

public class HemogramaVisualizarDTO {

    private long id;
    private String descricao;
    private String data;
    private double hemoglobina;
    private double hemacrotito;
    private double hematimetria;
    private double reticulocitos;
    private double volumeCorpuscular;
    private double hemoglobinaCorpuscular;
    private double concentracaoHemoglobina;
    private double indiceAnisocitose;

    public HemogramaVisualizarDTO(long id, String descricao, String data, double hemoglobina, double hemacrotito, double hematimetria, double reticulocitos, double volumeCorpuscular, double hemoglobinaCorpuscular, double concentracaoHemoglobina, double indiceAnisocitose) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
