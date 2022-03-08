package com.ufcg.es.healthtrack.filter;

import com.ufcg.es.healthtrack.service.JWTService;
import io.jsonwebtoken.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilter {

    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token inexistente ou mal formatado!");
            return;
            // throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = header.substring(TOKEN_INDEX);
        try {
            Jwts.parser().setSigningKey(JWTService.TOKEN_KEY).parseClaimsJws(token).getBody();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
                | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;// a requisição nem precisa passar adiante, retornar já para o cliente pois não
            // pode prosseguir daqui pra frente
            // por falta de autorização
        }

        filterChain.doFilter(request, servletResponse);
    }
}
