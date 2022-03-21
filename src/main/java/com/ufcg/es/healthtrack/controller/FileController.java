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

    //localhost:8080/api/v1/exame/pdf/upload
    //localhost:8080/api/v1/exame/pdf/download/{id}"
    //localhost:8080/api/v1/exame/pdf/{email}


    @Autowired
    private ExameService exameService;


    @PostMapping(value = "/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartfile, ServletRequest servletRequest, RedirectAttributes ra) throws IOException {

        try {
            exameService.uploadFile(multipartfile, getAuthorizationHeader(servletRequest));
            ra.addFlashAttribute("message", "The File has Been Uploaded Succesfully");
        } catch (SecurityException | IOException e) {

        }


//        String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
//        File file = new File();
//        Usuario user = this.userRepository.findById(email).get();
//        file.setUser(user);
//        file.setName(fileName);
//        file.setContent(multipartfile.getBytes());
//        file.setSize(multipartfile.getSize());
//        file.setUploadTime(LocalDateTime.now());
//        fileRepository.save(file);
    }


    @GetMapping(value = "/download/{id}")
    public void downloadFile(@PathVariable long id, HttpServletResponse response, ServletRequest servletRequest) throws Exception {

        try {
            File file = exameService.downloadFile(id, getAuthorizationHeader(servletRequest));

//        File file = fileRepository.findById(id).get();


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
            List<FileDTO> exames = this.exameService.listarTodosExamesPDFs(getAuthorizationHeader(servletRequest));
            return new ResponseEntity<>(exames, HttpStatus.OK);
        } catch (HealthTrackSystemException e) {
            return new ResponseEntity(new ExceptionResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    private String getAuthorizationHeader(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getHeader("Authorization");
    }




//    @GetMapping(value = "/download/{email}")
//	public ResponseEntity<List<File>> downloadFile(@PathVariable String email, HttpServletResponse response) throws Exception {
//		ArrayList<File> result = (ArrayList<File>) fileRepository.findByEmail(email);
//		return ResponseEntity.ok(result);
//	}



}
