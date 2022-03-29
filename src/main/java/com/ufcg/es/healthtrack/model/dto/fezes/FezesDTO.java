package com.ufcg.es.healthtrack.model.dto.fezes;

import java.time.LocalDateTime;

public class FezesDTO {

    private String descricao;
    private LocalDateTime date;
    private String aspectoGeral;
    private String protozoarios;
    private String helmintos;
    private String metodo;
    private String observacoes;

    public FezesDTO() {
    }

    public FezesDTO(String descricao, LocalDateTime date, String aspectoGeral, String protozoarios, String helmintos, String metodo, String observacoes) {
        this.descricao = descricao;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
}
