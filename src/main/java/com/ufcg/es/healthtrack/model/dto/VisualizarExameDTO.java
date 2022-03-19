package com.ufcg.es.healthtrack.model.dto;

public class VisualizarExameDTO {

    private long id;

    public VisualizarExameDTO(){}

    public VisualizarExameDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
