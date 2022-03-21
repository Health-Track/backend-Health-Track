package com.ufcg.es.healthtrack.model.dto.urina;

import java.time.LocalDateTime;

public class UrinaDTO {

    private String descricao;
    private LocalDateTime data;
    private String aspecto;
    private String densidade;
    private String reacao;
    private String proteinas;
    private String corposCetonicos;
    private String glicose;
    private String bilirrubina;
    private String urobilinogenio;
    private String sangueHb;
    private String nitrito;
    private String hemacias;
    private String leucocitos;
    private String celulasEpiteliais;
    private String cilindros;
    private String cristais;
    private String bacterias;
    private String filamentoDeMuco;
    private String outrosElementos;

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getAspecto() {
        return aspecto;
    }

    public String getDensidade() {
        return densidade;
    }

    public String getReacao() {
        return reacao;
    }

    public String getProteinas() {
        return proteinas;
    }

    public String getCorposCetonicos() {
        return corposCetonicos;
    }

    public String getGlicose() {
        return glicose;
    }

    public String getBilirrubina() {
        return bilirrubina;
    }

    public String getUrobilinogenio() {
        return urobilinogenio;
    }

    public String getSangueHb() {
        return sangueHb;
    }

    public String getNitrito() {
        return nitrito;
    }

    public String getHemacias() {
        return hemacias;
    }

    public String getLeucocitos() {
        return leucocitos;
    }

    public String getCelulasEpiteliais() {
        return celulasEpiteliais;
    }

    public String getCilindros() {
        return cilindros;
    }

    public String getCristais() {
        return cristais;
    }

    public String getBacterias() {
        return bacterias;
    }

    public String getFilamentoDeMuco() {
        return filamentoDeMuco;
    }

    public String getOutrosElementos() {
        return outrosElementos;
    }
}
