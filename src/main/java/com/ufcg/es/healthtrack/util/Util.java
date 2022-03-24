package com.ufcg.es.healthtrack.util;

public class Util {

    private static final String EMAIL_REGEX = "^([a-zA-Z0-9\\._]+)@([a-zA-Z0-9])+.([a-z]+)(.[a-z]+)?$";

    private static final String SENHA_REGEX = "^.{8,}$";

    public static void verificaNull(Object objeto, String nomeObjeto) {
        if(objeto == null) {
            throw new IllegalArgumentException(String.format("O atributo %s não pode ser nulo.", nomeObjeto));
        }
    }

    public static void verificaStringVazia(String string, String nomeAtributo) {
        if(string.trim().equals("")) {
            throw new IllegalArgumentException(String.format("%s não pode vazio.", nomeAtributo));
        }
    }

    public static void verificaFormatoEmail(String email) {
        if(!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException(String.format("%s não é um e-mail válido.", email));
        }
    }

    public static void verificaFormatoSenha(String senha) {
        if(!senha.matches(SENHA_REGEX)) {
            throw new IllegalArgumentException("A senha informada não é válida, informe uma senha com pelo menos 8 dígitos.");
        }
    }
}
