package com.ufcg.es.healthtrack.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.File;
import com.ufcg.es.healthtrack.model.dto.ExceptionResponse;
import com.ufcg.es.healthtrack.model.dto.FileDTO;
import com.ufcg.es.healthtrack.service.ExameService;
import com.ufcg.es.healthtrack.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("exame/pdf")
public class FileController {

    @Autowired
    private ExameService exameService;


    @PostMapping(value = "/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartfile, ServletRequest servletRequest, RedirectAttributes ra) throws IOException {

        try {
            exameService.uploadFile(multipartfile, Util.getAuthorizationHeader(servletRequest));
            ra.addFlashAttribute("message", "The File has Been Uploaded Succesfully");
        } catch (SecurityException | IOException e) {

        }
    }


    @GetMapping(value = "/download/{id}")
    public void downloadFile(@PathVariable long id, HttpServletResponse response, ServletRequest servletRequest) throws Exception {

        try {
            File file = exameService.downloadFile(id, Util.getAuthorizationHeader(servletRequest));

            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + file.getName();
            response.setHeader(headerKey, headerValue);

            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(file.getContent());
            outputStream.close();
        } catch (HealthTrackSystemException e) {
            response.sendError(400, e.getMessage());
        }

    }

    @GetMapping("/listar")
    public ResponseEntity listar(ServletRequest servletRequest) {
        try {
            List<FileDTO> exames = this.exameService.listarTodosExamesPDFs(Util.getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(exames, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
