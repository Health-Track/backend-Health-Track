package com.ufcg.es.healthtrack.model.dto.urina;

import java.time.LocalDateTime;

public class UrinaVisualizarDTO {

    private long id;
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

    public UrinaVisualizarDTO(long id, String descricao, LocalDateTime data, String aspecto, String reacao,
                              String densidade, String proteinas, String corposCetonicos, String glicose,
                              String bilirrubina, String urobilinogenio, String sangueHb, String nitrito,
                              String hemacias, String leucocitos, String celulasEpiteliais, String cilindros,
                              String cristais, String bacterias, String filamentoDeMuco, String outrosElementos) {
        this.id = id;
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

    public long getId() {
        return id;
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
