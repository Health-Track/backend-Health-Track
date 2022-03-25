package com.ufcg.es.healthtrack.controller.exame;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaVisualizarDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import com.ufcg.es.healthtrack.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/exame/hemograma")
public class HemogramaController {

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody HemogramaDTO exame, ServletRequest servletRequest) {
        try {
            this.exameService.cadastrarExameHemograma(exame, Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity listarTodos(ServletRequest servletRequest) {
        try {
            List<HemogramaVisualizarDTO> list = this.exameService.listarTodosExamesHemograma(Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity(list,HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity visualizarExame(@PathVariable long id, ServletRequest servletRequest) {
        try {
            HemogramaVisualizarDTO exame = this.exameService.visualizarExamesHemograma(id, Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity(exame,HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}