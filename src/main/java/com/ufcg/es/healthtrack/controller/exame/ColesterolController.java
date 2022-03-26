package com.ufcg.es.healthtrack.controller.exame;


import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolDTO;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolVisualizarDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import com.ufcg.es.healthtrack.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@RestController
@RequestMapping("/exame/colesterol")
public class ColesterolController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody ColesterolDTO colesterolDTO, ServletRequest servletRequest) {
        try {
            this.exameService.cadastrarExameColesterol(colesterolDTO, Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity listarTodos(ServletRequest servletRequest) {
        try {
            return new ResponseEntity(this.exameService.listarTodosExamesColesterol(Util.getAuthorizationHeader(servletRequest)), HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity visualizarExame(@PathVariable long id, ServletRequest servletRequest) {
        try {
            ColesterolVisualizarDTO exame = this.exameService.visualizarExameColesterol(id,Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity(exame, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}