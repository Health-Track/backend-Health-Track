package com.ufcg.es.healthtrack.model.dto.glicemia;

import java.time.LocalDateTime;

public class GlicemiaVisualizarDTO {

    private long id;

    private int medicao;

    private LocalDateTime dataMedicao;

    public GlicemiaVisualizarDTO(long id, int medicao, LocalDateTime dataMedicao) {
        this.id = id;
        this.medicao = medicao;
        this.dataMedicao = dataMedicao;
    }

    public int getMedicao() {
        return medicao;
    }

    public void setMedicao(int medicao) {
        this.medicao = medicao;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(LocalDateTime dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
