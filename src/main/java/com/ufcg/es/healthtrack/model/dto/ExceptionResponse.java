package com.ufcg.es.healthtrack.model.dto;

public class ExceptionResponse {

    private String msg;

    public ExceptionResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
