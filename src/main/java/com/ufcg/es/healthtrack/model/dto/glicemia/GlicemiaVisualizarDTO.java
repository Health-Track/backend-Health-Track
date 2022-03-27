package com.ufcg.es.healthtrack.model.dto.glicemia;

import java.time.LocalDateTime;

public class GlicemiaVisualizarDTO {

    private long id;

    private int medicao;

    private String data;

    public GlicemiaVisualizarDTO(long id, int medicao, String data) {
        this.id = id;
        this.medicao = medicao;
        this.data = data;
    }

    public GlicemiaVisualizarDTO() {
    }

    public int getMedicao() {
        return medicao;
    }

    public void setMedicao(int medicao) {
        this.medicao = medicao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
