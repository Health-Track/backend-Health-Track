package com.ufcg.es.healthtrack.exception;

public class ExameNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = -2558524431457472634L;

    public ExameNaoEncontradoException(String msg) {
        super(msg);
    }
}
