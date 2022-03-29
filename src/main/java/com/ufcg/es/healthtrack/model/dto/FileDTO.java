package com.ufcg.es.healthtrack.model.dto;

public class FileDTO {

    private long id;
    private String name;

    public FileDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FileDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
