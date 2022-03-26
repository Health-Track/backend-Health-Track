package com.ufcg.es.healthtrack.controller.exame;

import java.util.List;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesVisualizarDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import com.ufcg.es.healthtrack.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;


@RestController
@RequestMapping(value = "/exame/fezes")
public class FezesController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody FezesDTO exameDTO, ServletRequest servletRequest) {
          try {
            this.exameService.cadastrarExameFezes(exameDTO, Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar")
    public ResponseEntity listarTodos(ServletRequest servletRequest) {
        try {
            List<FezesVisualizarDTO> exames = this.exameService.listarTodosExamesFezes(Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(exames, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity visualizarExame(@PathVariable long id, ServletRequest servletRequest) {
        try {
            FezesVisualizarDTO exame = this.exameService.visualizarExameFezes(id, Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity(exame, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
