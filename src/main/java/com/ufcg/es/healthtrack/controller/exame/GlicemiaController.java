package com.ufcg.es.healthtrack.controller.exame;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.GlicemiaDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/exame/glicemia")
public class GlicemiaController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody GlicemiaDTO glicemiaDTO, ServletRequest servletRequest) {
        try {
            this.exameService.cadastrarExameGlicemia(glicemiaDTO,getAuthorizationHeader(servletRequest));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (HealthTrackSystemException | SecurityException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity listarTodos(ServletRequest servletRequest) {
        try {
            return new ResponseEntity<List<GlicemiaDTO>>(this.exameService.listarTodosExamesGlicemia(getAuthorizationHeader(servletRequest)),HttpStatus.OK);
        } catch (HealthTrackSystemException | SecurityException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity visializarExame(@RequestBody LocalDateTime dataMedicao , ServletRequest servletRequest) {
        try {
            return new ResponseEntity<>(this.exameService.visualizarExameGlicemia(dataMedicao,getAuthorizationHeader(servletRequest)), HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    private String getAuthorizationHeader(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("Authorization");
    }


}
