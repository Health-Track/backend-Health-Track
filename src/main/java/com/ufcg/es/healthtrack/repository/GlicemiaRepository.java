package com.ufcg.es.healthtrack.repository;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.exame.Glicemia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {

    List<Glicemia> findAllByUsuario(Usuario usuario);

    Optional<Glicemia> findByDataMedicaoAndUsuario(LocalDateTime dataMedicao, Usuario usuario);


}
