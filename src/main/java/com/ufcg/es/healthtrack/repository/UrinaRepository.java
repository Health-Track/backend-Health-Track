package com.ufcg.es.healthtrack.repository;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.exame.ExameUrina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrinaRepository extends JpaRepository<ExameUrina, Long> {

    List<ExameUrina> findAllByUsuario(Usuario usuario);

    Optional<ExameUrina> findByIdAndUsuario(long id, Usuario usuario);
}
