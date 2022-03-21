package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.ExameUrina;
import com.ufcg.es.healthtrack.repository.UrinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExameUrinaService {

    @Autowired
    private UrinaRepository urinaRepository;

    public void adicionaExame(UrinaDTO urinaDTO, Usuario usuario) {
        ExameUrina exameUrina = criaExameUrina(urinaDTO, usuario);
        this.urinaRepository.save(exameUrina);
    }

    private ExameUrina criaExameUrina(UrinaDTO urinaDTO, Usuario usuario) {
        return new ExameUrina(usuario, urinaDTO.getDescricao(), urinaDTO.getData(), urinaDTO.getAspecto(), urinaDTO.getDensidade(),
                urinaDTO.getReacao(), urinaDTO.getProteinas(), urinaDTO.getCorposCetonicos(), urinaDTO.getGlicose(),
                urinaDTO.getBilirrubina(), urinaDTO.getUrobilinogenio(), urinaDTO.getSangueHb(), urinaDTO.getNitrito(),
                urinaDTO.getHemacias(), urinaDTO.getLeucocitos(), urinaDTO.getCelulasEpiteliais(), urinaDTO.getCilindros(),
                urinaDTO.getCristais(), urinaDTO.getBacterias(), urinaDTO.getFilamentoDeMuco(), urinaDTO.getOutrosElementos());
    }

    public List<UrinaVisualizarDTO> listarTodosPorUsuario(Usuario usuario) {
        List<UrinaVisualizarDTO> list = new ArrayList<>();
        for (ExameUrina exame : this.urinaRepository.findAllByUsuario(usuario)) {
            list.add(getUrinaVisualizarDTO(exame));
        }
        return list;
    }

    private UrinaVisualizarDTO getUrinaVisualizarDTO(ExameUrina exame) {
        return new UrinaVisualizarDTO(exame.getId(), exame.getDescricao(), exame.getData(), exame.getAspecto(),
                exame.getDensidade(), exame.getReacao(), exame.getProteinas(), exame.getCorposCetonicos(), exame.getGlicose(),
                exame.getBilirrubina(), exame.getUrobilinogenio(), exame.getSangueHb(), exame.getNitrito(),
                exame.getHemacias(), exame.getLeucocitos(), exame.getCelulasEpiteliais(), exame.getCilindros(),
                exame.getCristais(), exame.getBacterias(), exame.getFilamentoDeMuco(), exame.getOutrosElementos());
    }

    public UrinaVisualizarDTO visualizarExame(VisualizarExameDTO dto, Usuario usuarioLogado) {
        Optional<ExameUrina> optExame = this.urinaRepository.findByIdAndUsuario(dto.getId(), usuarioLogado);
        if(optExame.isEmpty()) {
            throw new HealthTrackSystemException("Exame não encontrado");
        }
        return getUrinaVisualizarDTO(optExame.get());
    }

}
