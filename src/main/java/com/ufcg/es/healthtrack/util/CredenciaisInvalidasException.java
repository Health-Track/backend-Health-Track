package com.ufcg.es.healthtrack.util;

public class CredenciaisInvalidasException extends  RuntimeException {
    private static final long serialVersionUID = -2558524431457472634L;

    public CredenciaisInvalidasException(String msg) {
        super(msg);
    }
}
