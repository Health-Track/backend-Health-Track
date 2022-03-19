package com.ufcg.es.healthtrack.model.dto.pressao;

public class PressaoDTO {

    private int sistolica;

    private int diastolica;

    private String observacoes;

    public PressaoDTO(int sistolica, int diastolica, String observacoes){
        this.sistolica = sistolica;
        this.diastolica = diastolica;
        this.observacoes = observacoes;
    }

    public int getSistolica() {
        return sistolica;
    }

    public int getDiastolica(){
        return diastolica;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setSistolica(int sistolica) {
        this.sistolica = sistolica;
    }

    public void setDiastolica(int diastolica) {
        this.diastolica = diastolica;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
