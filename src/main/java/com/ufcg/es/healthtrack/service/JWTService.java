package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.filter.JWTFilter;
import com.ufcg.es.healthtrack.model.dto.Credenciais;
import com.ufcg.es.healthtrack.model.dto.LoginResponse;
import com.ufcg.es.healthtrack.util.CredenciaisInvalidasException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    public static final String TOKEN_KEY = "asdfasdfasdfsfdsawaer";

    @Autowired
    private UsuarioService usuarioService;

    public LoginResponse autentica(Credenciais credenciais) throws CredenciaisInvalidasException{

        validaUsuario(credenciais);
        String token = geraToken(credenciais);

        return new LoginResponse(token);
    }




    private void validaUsuario(Credenciais credenciais) {
        if (!this.usuarioService.validaUsuario(credenciais.getEmail(),credenciais.getSenha())){
            throw new CredenciaisInvalidasException("Credenciais Inválidas.");
        }
    }

    private String geraToken(Credenciais credenciais) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(credenciais.getEmail())
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .compact();
    }

    public String getEmailUsuarioLogado(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(JWTFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }
}
