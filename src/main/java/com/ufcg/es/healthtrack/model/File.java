package com.ufcg.es.healthtrack.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    private String name;

    private Long size;

    private LocalDateTime uploadTime;

    @Lob
    private byte[] content;

    public File() {}

    public File(Long id, String name, Long size, Usuario usuario, byte[] content) {
        super();
        this.id = id;
        this.name = name;
        this.size = size;
        this.usuario = usuario;
        this.content = content;
    }

    public Usuario getUser() {
        return usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Usuario user) {
        this.usuario = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
