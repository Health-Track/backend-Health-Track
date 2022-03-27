package com.ufcg.es.healthtrack.model.dto.colesterol;

public class ColesterolVisualizarDTO {

    private long id;
    private String data;
    private String descricao;
    private int colesterolTotal;
    private int colesterolHDL;
    private int colesterolNaoHDL;
    private int colesterolLDL;
    private double relacaoTotalHDL;

    public ColesterolVisualizarDTO(long id, String data, String descricao, int colesterolTotal, int colesterolHDL, int colesterolNaoHDL, int colesterolLDL, double relacaoTotalHDL) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.colesterolTotal = colesterolTotal;
        this.colesterolHDL = colesterolHDL;
        this.colesterolNaoHDL = colesterolNaoHDL;
        this.colesterolLDL = colesterolLDL;
        this.relacaoTotalHDL = relacaoTotalHDL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getColesterolTotal() {
        return colesterolTotal;
    }

    public void setColesterolTotal(int colesterolTotal) {
        this.colesterolTotal = colesterolTotal;
    }

    public int getColesterolHDL() {
        return colesterolHDL;
    }

    public void setColesterolHDL(int colesterolHDL) {
        this.colesterolHDL = colesterolHDL;
    }

    public int getColesterolNaoHDL() {
        return colesterolNaoHDL;
    }

    public void setColesterolNaoHDL(int colesterolNaoHDL) {
        this.colesterolNaoHDL = colesterolNaoHDL;
    }

    public int getColesterolLDL() {
        return colesterolLDL;
    }

    public void setColesterolLDL(int colesterolLDL) {
        this.colesterolLDL = colesterolLDL;
    }

    public double getRelacaoTotalHDL() {
        return relacaoTotalHDL;
    }

    public void setRelacaoTotalHDL(double relacaoTotalHDL) {
        this.relacaoTotalHDL = relacaoTotalHDL;
    }
}
