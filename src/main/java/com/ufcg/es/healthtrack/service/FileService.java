package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.File;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public void uploadFile(MultipartFile multipartfile, Usuario usuario) throws IOException {
        String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
        File file = new File();
        file.setUser(usuario);
        file.setName(fileName);
        file.setContent(multipartfile.getBytes());
        file.setSize(multipartfile.getSize());
        file.setUploadTime(LocalDateTime.now());
        fileRepository.save(file);
    }

    public File getFile(long id) {
        return fileRepository.findById(id).get();
    }
}