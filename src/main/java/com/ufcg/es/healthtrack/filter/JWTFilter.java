package com.ufcg.es.healthtrack.filter;

import com.ufcg.es.healthtrack.service.JWTService;
import com.ufcg.es.healthtrack.service.UsuarioService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilter {

    public final static int TOKEN_INDEX = 7;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token inexistente ou mal formatado!");
            return;
        }

        String token = header.substring(TOKEN_INDEX);
        try {
            String emailUsuario = Jwts.parser().setSigningKey(JWTService.TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
            if (!usuarioService.usuarioExiste(emailUsuario)) {
                throw new SignatureException("Erro durante a autenticação do usuário.");
            }
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
                | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        filterChain.doFilter(request, servletResponse);
    }
}
