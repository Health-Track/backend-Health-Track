package com.ufcg.es.healthtrack.model.dto;

public class LoginResponse {

    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String jwtToken) {
        this.token = jwtToken;
    }

    public String getToken() {
        return token;
    }

    public void setTokenoken(String jwtToken) {
        this.token = jwtToken;
    }
}
