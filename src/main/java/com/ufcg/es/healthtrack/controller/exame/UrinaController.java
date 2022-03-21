package com.ufcg.es.healthtrack.controller.exame;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaVisualizarDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/exame/urina")
public class UrinaController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity createExameUrina(@RequestBody UrinaDTO urinaDTO, ServletRequest servletRequest) {
        try {
            this.exameService.cadastrarExameUrina(urinaDTO, getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    private String getAuthorizationHeader(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("Authorization");
    }

    @GetMapping(value = "/listar")
    public ResponseEntity listExameUrina(ServletRequest servletRequest) {
        try {
            List<UrinaVisualizarDTO> exames = this.exameService.listarTodosExamesUrina(getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(exames, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity visualizarExame(@PathVariable long id, ServletRequest servletRequest) {
        try {
            UrinaVisualizarDTO exame = this.exameService.visualizarExameUrina(id, getAuthorizationHeader(servletRequest));
            return new ResponseEntity(exame, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
