package com.ufcg.es.healthtrack.model.dto.pressao;

public class PressaoVisualizarDTO {

    private long id;
    private int sistolica;
    private int diastolica;
    private String observacoes;

    public PressaoVisualizarDTO(long id, int sistolica, int diastolica, String observacoes) {
        this.id = id;
        this.sistolica = sistolica;
        this.diastolica = diastolica;
        this.observacoes = observacoes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSistolica() {
        return sistolica;
    }

    public void setSistolica(int sistolica) {
        this.sistolica = sistolica;
    }

    public int getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(int diastolica) {
        this.diastolica = diastolica;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
