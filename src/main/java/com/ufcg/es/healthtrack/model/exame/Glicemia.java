package com.ufcg.es.healthtrack.model.exame;

import com.ufcg.es.healthtrack.model.Usuario;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Glicemia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dataMedicao;

    private int medicao;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    public Glicemia(){}

    public Glicemia(Usuario usuario, int medicao, LocalDateTime dataMedicao) {
        this.usuario = usuario;
        this.medicao = medicao;
        this.dataMedicao = dataMedicao;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public int getMedicao() {
        return medicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long l) {
        this.id = id;
    }
}
