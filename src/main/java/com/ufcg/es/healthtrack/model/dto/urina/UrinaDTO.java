package com.ufcg.es.healthtrack.model.dto.urina;

import java.time.LocalDateTime;

public class UrinaDTO {

    private String descricao;
    private LocalDateTime data;
    private String aspecto;
    private double densidade;
    private String reacao;
    private String proteinas;
    private String corposCetonicos;
    private String glicose;
    private String bilirrubina;
    private String urobilinogenio;
    private String sangueHb;
    private String nitrito;
    private double hemacias;
    private double leucocitos;
    private double celulasEpiteliais;
    private String cilindros;
    private String cristais;
    private String bacterias;
    private String filamentoDeMuco;
    private String outrosElementos;

    public UrinaDTO(String descricao, LocalDateTime data, String aspecto, double densidade, String reacao, String proteinas, String corposCetonicos, String glicose, String bilirrubina, String urobilinogenio, String sangueHb, String nitrito, double hemacias, double leucocitos, double celulasEpiteliais, String cilindros, String cristais, String bacterias, String filamentoDeMuco, String outrosElementos) {
        this.descricao = descricao;
        this.data = data;
        this.aspecto = aspecto;
        this.densidade = densidade;
        this.reacao = reacao;
        this.proteinas = proteinas;
        this.corposCetonicos = corposCetonicos;
        this.glicose = glicose;
        this.bilirrubina = bilirrubina;
        this.urobilinogenio = urobilinogenio;
        this.sangueHb = sangueHb;
        this.nitrito = nitrito;
        this.hemacias = hemacias;
        this.leucocitos = leucocitos;
        this.celulasEpiteliais = celulasEpiteliais;
        this.cilindros = cilindros;
        this.cristais = cristais;
        this.bacterias = bacterias;
        this.filamentoDeMuco = filamentoDeMuco;
        this.outrosElementos = outrosElementos;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getAspecto() {
        return aspecto;
    }

    public double getDensidade() {
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

    public double getHemacias() {
        return hemacias;
    }

    public double getLeucocitos() {
        return leucocitos;
    }

    public double getCelulasEpiteliais() {
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
