package com.ufcg.es.healthtrack.model.dto;

import java.time.LocalDateTime;

public class ColesterolDTO {

    private LocalDateTime dataMedicao;

    private String descricao;

    private int colesterolTotal;

    private int colesterolHDL;

    private int colesterolNaoHDL;

    private int colesterolLDL;

    private int relacaoTotalHDL;

    public ColesterolDTO(String descricao, LocalDateTime dataMedicao, int colesterolTotal,
                         int colesterolHDL, int colesterolNaoHDL, int colesterolLDL, int relacaoTotalHDL) {
        this.descricao = descricao;
        this.dataMedicao = dataMedicao;
        this.colesterolTotal = colesterolTotal;
        this.colesterolHDL = colesterolHDL;
        this.colesterolNaoHDL = colesterolNaoHDL;
        this.colesterolLDL = colesterolLDL;
        this.relacaoTotalHDL = relacaoTotalHDL;
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

    public int getRelacaoTotalHDL() {
        return relacaoTotalHDL;
    }

    public void setRelacaoTotalHDL(int relacaoTotalHDL) {
        this.relacaoTotalHDL = relacaoTotalHDL;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(LocalDateTime dataMedicao) {
        this.dataMedicao = dataMedicao;
    }


}
