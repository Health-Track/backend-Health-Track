package com.ufcg.es.healthtrack.controller;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ufcg.es.healthtrack.model.File;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ufcg.es.healthtrack.repository.FileRepository;
import com.ufcg.es.healthtrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exame/pdf")
public class FileController {

    //localhost:8080/api/v1/exame/pdf/upload
    //localhost:8080/api/v1/exame/pdf/download/{id}"
    //localhost:8080/api/v1/exame/pdf/{email}


    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UsuarioRepository userRepository;

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

        File file = exameService.downloadFile(id, getAuthorizationHeader(servletRequest));

//        File file = fileRepository.findById(id).get();


        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName();
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(file.getContent());
        outputStream.close();

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
