package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.ExameFezes;
import com.ufcg.es.healthtrack.repository.FezesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExameFezesService {

    @Autowired
    private FezesRepository fezesRepository;

    public void adicionaExame(FezesDTO exameDTO, Usuario usuario) {
        ExameFezes exameFezes = criaExameFezes(exameDTO,usuario);
        this.fezesRepository.save(exameFezes);
    }

    private ExameFezes criaExameFezes(FezesDTO exameDTO, Usuario usuario) {
        return new ExameFezes(usuario, exameDTO.getDescricao(),
                exameDTO.getDate(), exameDTO.getAspectoGeral(),
                exameDTO.getProtozoarios(), exameDTO.getHelmintos(),
                exameDTO.getMetodo(), exameDTO.getObservacoes());
    }

    public List<FezesVisualizarDTO> listarTodosPorUsuario(Usuario usuario) {
        List<FezesVisualizarDTO> list = new ArrayList<>();
        for (ExameFezes exame : this.fezesRepository.findAllByUsuario(usuario)) {
            list.add(getFezesVisualizarDTO(exame));
        }
        return list;
    }

    public FezesVisualizarDTO visualizarExame(long id, Usuario usuarioLogado) {
        Optional<ExameFezes> optExame = this.fezesRepository.findByIdAndUsuario(id, usuarioLogado);
        if(optExame.isEmpty()) {
            throw new HealthTrackSystemException("Exame não encontrado");
        }

        return getFezesVisualizarDTO(optExame.get());
    }

    private FezesVisualizarDTO getFezesVisualizarDTO(ExameFezes exame) {
        return new FezesVisualizarDTO(exame.getId(), exame.getDescricao(), exame.getDate(), exame.getAspectoGeral(),
                exame.getProtozoarios(), exame.getHelmintos(), exame.getMetodo(), exame.getObservacoes());
    }
}
