package com.ufcg.es.healthtrack.exception;

public class HealthTrackSystemException extends  RuntimeException {
    private static final long serialVersionUID = -2558524431457472634L;

    public HealthTrackSystemException(String msg) {
        super(msg);
    }
}