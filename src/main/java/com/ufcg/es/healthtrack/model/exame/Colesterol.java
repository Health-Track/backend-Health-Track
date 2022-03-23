package com.ufcg.es.healthtrack.model.exame;

import com.ufcg.es.healthtrack.model.Usuario;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Colesterol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    private LocalDateTime dataMedicao;

    private String descricao;

    private int colesterolTotal;

    private int colesterolHDL;

    private int colesterolNaoHDL;

    private int colesterolLDL;

    private double relacaoTotalHDL;

    public Colesterol(){}

    public Colesterol(Usuario usuario, String descricao, LocalDateTime dataMedicao, int colesterolTotal,
                      int colesterolHDL, int colesterolNaoHDL, int colesterolLDL, double relacaoTotalHDL){
        this.usuario = usuario;
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

    public int getColesterolTotal() {
        return colesterolTotal;
    }

    public int getColesterolHDL() {
        return colesterolHDL;
    }

    public int getColesterolNaoHDL() {
        return colesterolNaoHDL;
    }

    public int getColesterolLDL() {
        return colesterolLDL;
    }

    public double getRelacaoTotalHDL() {
        return relacaoTotalHDL;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

