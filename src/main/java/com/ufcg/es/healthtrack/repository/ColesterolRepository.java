package com.ufcg.es.healthtrack.repository;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.exame.Colesterol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColesterolRepository extends JpaRepository<Colesterol, Long> {

    List<Colesterol> findAllByUsuario(Usuario usuario);
}
