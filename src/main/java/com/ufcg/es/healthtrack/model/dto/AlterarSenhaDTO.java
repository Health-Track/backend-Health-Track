package com.ufcg.es.healthtrack.model.dto;

public class AlterarSenhaDTO {
    private String senhaAntiga;
    private String senhaNova;

    public AlterarSenhaDTO(String senhaAntiga, String senhaNova) {
        this.senhaAntiga = senhaAntiga;
        this.senhaNova = senhaNova;
    }

    public AlterarSenhaDTO() {
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }
}
