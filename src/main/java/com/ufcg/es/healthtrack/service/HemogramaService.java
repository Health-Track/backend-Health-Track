package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.Hemograma;
import com.ufcg.es.healthtrack.repository.HemogramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HemogramaService {

    @Autowired
    private HemogramaRepository hemogramaRepository;


    public void adicionarExame(HemogramaDTO exame, Usuario usuario) {
        Hemograma hemograma = criarExame(exame,usuario);
        this.hemogramaRepository.save(hemograma);
    }

    private Hemograma criarExame(HemogramaDTO exame,Usuario usuario) {
        return new Hemograma(usuario, exame.getDescricao(), exame.getDate(),
                exame.getHemoglobina(),exame.getHemacrotito(),exame.getHematimetria(),
                exame.getReticulocitos(),exame.getVolumeCorpuscular(),exame.getHemoglobinaCorpuscular(),
                exame.getConcentracaoHemoglobina(),exame.getIndiceAnisocitose());
    }

    public List<HemogramaVisualizarDTO> listarTodosPorUsuario(Usuario usuario) {
        List<HemogramaVisualizarDTO> list = new ArrayList<>();
        for(Hemograma exame : this.hemogramaRepository.findAllByUsuario(usuario)){
            list.add(getHemogramaVisualizarDTO(exame));
        }
        return list;
    }

    private HemogramaVisualizarDTO getHemogramaVisualizarDTO(Hemograma exame) {
        return new HemogramaVisualizarDTO(exame.getId(), exame.getDescricao(), exame.getDate(),
                exame.getHemoglobina(),exame.getHemacrotito(), exame.getHematimetria(),
                exame.getReticulocitos(), exame.getVolumeCorpuscular(),
                exame.getHemoglobinaCorpuscular(), exame.getConcentracaoHemoglobina(),
                exame.getIndiceAnisocitose());
    }

    public HemogramaVisualizarDTO visualizarExame(VisualizarExameDTO dto, Usuario usuarioLogado) {
        Optional<Hemograma> optExame = this.hemogramaRepository.findById(dto.getId());
        if(optExame.isEmpty()){
            throw new HealthTrackSystemException("Exame não encontrado");
        }
        return getHemogramaVisualizarDTO(optExame.get());
    }
}
