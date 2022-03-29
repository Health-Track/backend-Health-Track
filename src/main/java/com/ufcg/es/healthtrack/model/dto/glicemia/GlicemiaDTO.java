package com.ufcg.es.healthtrack.model.dto.glicemia;

import java.time.LocalDateTime;

public class GlicemiaDTO {

    private int medicao;

    private LocalDateTime dataMedicao;

    public GlicemiaDTO(int medicao, LocalDateTime dataMedicao) {
        this.medicao = medicao;
        this.dataMedicao = dataMedicao;
    }

    public GlicemiaDTO() {
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
}
