package com.ufcg.es.healthtrack.model.dto.fezes;

import java.time.LocalDateTime;

public class FezesVisualizarDTO {

    private long id;
    private String descricao;
    private String data;
    private String aspectoGeral;
    private String protozoarios;
    private String helmintos;
    private String metodo;
    private String observacoes;

    public FezesVisualizarDTO(long id, String descricao, String data, String aspectoGeral, String protozoarios, String helmintos, String metodo, String observacoes) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.aspectoGeral = aspectoGeral;
        this.protozoarios = protozoarios;
        this.helmintos = helmintos;
        this.metodo = metodo;
        this.observacoes = observacoes;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
