package com.ufcg.es.healthtrack.repository;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.exame.Pressao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PressaoRepository extends JpaRepository<Pressao, Long> {

    List<Pressao> findAllByUsuario(Usuario usuario);

    Optional<Pressao> findByIdAndUsuario(long id, Usuario usuario);
}
