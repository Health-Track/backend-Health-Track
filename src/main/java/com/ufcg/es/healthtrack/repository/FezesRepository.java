package com.ufcg.es.healthtrack.repository;

import java.util.List;
import java.util.Optional;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.exame.ExameFezes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FezesRepository extends JpaRepository<ExameFezes, Long>{

    List<ExameFezes> findAllByUsuario(Usuario usuario);

    Optional<ExameFezes> findByIdAndUsuario(long id, Usuario usuario);
}

