package com.ufcg.es.healthtrack.controller;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.AlterarSenhaDTO;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.UsuarioDTO;
import com.ufcg.es.healthtrack.service.JWTService;
import com.ufcg.es.healthtrack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.cadastrarUsuario(usuarioDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/alterar")
    public ResponseEntity alterarSenha(@RequestBody AlterarSenhaDTO dto, ServletRequest servletRequest) {
        try {
            String emailUsuarioLogado = this.jwtService.getEmailUsuarioLogado(getAuthorizationHeader(servletRequest));
            usuarioService.alterarSenhaUsuario(dto,emailUsuarioLogado);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    private String getAuthorizationHeader(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("Authorization");
    }
}
