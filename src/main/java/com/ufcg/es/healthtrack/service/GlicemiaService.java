package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.Glicemia;
import com.ufcg.es.healthtrack.repository.GlicemiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GlicemiaService {

    @Autowired
    private GlicemiaRepository repository;

    public void adicionarMedicao(GlicemiaDTO glicemiaDTO,Usuario usuario) {
        verificaDataMedicao(glicemiaDTO.getDataMedicao(),usuario);
        Glicemia glicemia = criarGlicemia(glicemiaDTO,usuario);
        this.repository.save(glicemia);
    }

    public List<GlicemiaVisualizarDTO> listarTodosPorUsuario(Usuario usuarioLogado) {
        List<Glicemia> exames = this.repository.findAllByUsuario(usuarioLogado);
        return transformaParaVisializarDTO(exames);
    }

    public GlicemiaVisualizarDTO getExamePorId(long id, Usuario usuario) {
        Optional<Glicemia> optGlicemia = this.repository.findByIdAndUsuario(id,usuario);
        if(optGlicemia.isEmpty()) {
            throw new HealthTrackSystemException("O exame informado não foi encontrado.");
        }
        return transformaParaVisualizarDTO(optGlicemia.get());
    }

    private List<GlicemiaVisualizarDTO> transformaParaVisializarDTO(List<Glicemia> exames) {
        List<GlicemiaVisualizarDTO> examesDTO = new ArrayList<>();

        for(Glicemia exame: exames) {
            examesDTO.add(transformaParaVisualizarDTO(exame));
        }
        return examesDTO;
    }

    private GlicemiaVisualizarDTO transformaParaVisualizarDTO(Glicemia exame) {
        return new GlicemiaVisualizarDTO(exame.getId(), exame.getMedicao(), exame.getDataMedicao());
    }

    private void verificaDataMedicao(LocalDateTime dataMedicao, Usuario usuario){
        Optional<Glicemia> optGlicemia = this.repository.findByDataMedicaoAndUsuario(dataMedicao,usuario);
        if(optGlicemia.isPresent()) {
            throw new HealthTrackSystemException("Já existe uma medição de glicemia no momento informado");
        }
    }

    private Glicemia criarGlicemia(GlicemiaDTO glicemiaDTO,Usuario usuario) {
        return new Glicemia(usuario, glicemiaDTO.getMedicao(), glicemiaDTO.getDataMedicao());
    }


}
