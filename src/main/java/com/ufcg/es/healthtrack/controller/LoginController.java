package com.ufcg.es.healthtrack.controller;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.Credenciais;
import com.ufcg.es.healthtrack.model.dto.LoginResponse;
import com.ufcg.es.healthtrack.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    private JWTService jwtService;


    @PostMapping
    public ResponseEntity loginUsuario(@RequestBody Credenciais credenciais) {
      try {
          return new ResponseEntity<LoginResponse>(jwtService.autentica(credenciais), HttpStatus.OK);
      } catch (HealthTrackSystemException e) {
          return new ResponseEntity<LoginResponse>(new LoginResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
      }
    }
}
