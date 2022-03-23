package com.ufcg.es.healthtrack.repository;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.exame.Hemograma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HemogramaRepository extends JpaRepository<Hemograma, Long> {

    List<Hemograma> findAllByUsuario(Usuario usuario);

    Optional<Hemograma> findByIdAndUsuario(long id, Usuario usuarioLogado);
}
