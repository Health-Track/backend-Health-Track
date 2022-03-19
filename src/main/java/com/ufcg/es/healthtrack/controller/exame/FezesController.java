package com.ufcg.es.healthtrack.controller.exame;

import java.util.List;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesDTO;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.ExameFezes;
import com.ufcg.es.healthtrack.repository.FezesRepository;
import com.ufcg.es.healthtrack.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/exame/fezes")
public class FezesController {

    @Autowired
    private ExameService exameService;

    @Autowired
    private FezesRepository exameFezesRepository;

    @PostMapping
    public ResponseEntity createExameFezes(@RequestBody FezesDTO exameDTO, ServletRequest servletRequest) {
          try {
            this.exameService.cadastrarExameFezes(exameDTO, getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity listExameFezes(ServletRequest servletRequest) {
        try {
            List<FezesVisualizarDTO> exames = this.exameService.listarTodosExamesFezes(getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(exames, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity visualizarExame(@RequestBody VisualizarExameDTO dto, ServletRequest servletRequest) {
        try {
            FezesVisualizarDTO exame = this.exameService.visializarExameFezes(dto, getAuthorizationHeader(servletRequest));
            return new ResponseEntity(exame, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    private String getAuthorizationHeader(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("Authorization");
    }

}