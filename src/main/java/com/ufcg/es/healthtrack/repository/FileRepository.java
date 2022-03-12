package com.ufcg.es.healthtrack.repository;

import com.ufcg.es.healthtrack.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT new File(f.id, f.name, f.size, f.usuario, f.content) FROM File f ORDER BY f.uploadTime")
    List<File> findAll();

    @Query("SELECT new File(f.id, f.name, f.size, f.usuario, f.content) FROM File f WHERE f.usuario.email = ?1")
    List<File> findByEmail(String email);
}
