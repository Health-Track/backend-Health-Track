package com.ufcg.es.healthtrack.controller.exame;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoVisualizarDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/exame/pressao")
public class PressaoController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody PressaoDTO pressaoDTO, ServletRequest servletRequest) {
        try {
            this.exameService.cadastrarExamePressao(pressaoDTO, getAuthorizationHeader(servletRequest));
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }

    @GetMapping("/listar")
    public ResponseEntity listarTodos(ServletRequest servletRequest) {
        try {
            return new ResponseEntity<List<PressaoVisualizarDTO>>(this.exameService.listaTodosExamesPressao(getAuthorizationHeader(servletRequest)),HttpStatus.OK);
        }
        catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity visualizarExame(@RequestBody VisualizarExameDTO dto, ServletRequest servletRequest) {
        try {
            PressaoVisualizarDTO exame = this.exameService.visualizarExamePressao(dto,getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(exame,HttpStatus.OK);
        }
        catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private String getAuthorizationHeader(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("Authorization");
    }
}