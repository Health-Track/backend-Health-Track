package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.File;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.FileDTO;
import com.ufcg.es.healthtrack.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public File getFile(long id, Usuario usuario) {
        Optional<File> optFile = fileRepository.findById(id);
        if (optFile.isPresent()) {
            File file = optFile.get();
            if (file.getUser().getEmail().equals(usuario.getEmail())) {
                return file;
            }
        }
        throw new HealthTrackSystemException("Exame não encontrado");
    }

    public List<FileDTO> listarTodosPorUsuario(Usuario usuario) {
        List<FileDTO> list = new ArrayList<>();

        for(File f: this.fileRepository.findAllByUsuario(usuario)){
            list.add(new FileDTO(f.getId(),f.getName()));
        }
        return list;
    }
}
