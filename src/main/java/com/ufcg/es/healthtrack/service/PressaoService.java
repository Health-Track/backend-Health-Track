package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;

import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.Pressao;
import com.ufcg.es.healthtrack.repository.PressaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PressaoService {

    @Autowired
    private PressaoRepository repository;

    public void adicionarMedicao(PressaoDTO pressaoDTO, Usuario usuario) {
        Pressao pressao = criarPressao(pressaoDTO, usuario);
        this.repository.save(pressao);
    }

    private Pressao criarPressao(PressaoDTO pressaoDTO, Usuario usuario) {
        return new Pressao(usuario, pressaoDTO.getSistolica(), pressaoDTO.getDiastolica(),
                           pressaoDTO.getObservacoes());
    }

    public List<PressaoVisualizarDTO> listarTodosPorUsuario(Usuario usuarioLogado) {
        List<Pressao> exames = this.repository.findAllByUsuario(usuarioLogado);
        return transformaParaVisualizarDTO(exames);
    }

    private List<PressaoVisualizarDTO> transformaParaVisualizarDTO(List<Pressao> exames) {
        List<PressaoVisualizarDTO> examesDTO = new ArrayList<>();

        for (Pressao exame : exames) {
            examesDTO.add(transformaParaVisualizarDTO(exame));
        }
        return examesDTO;
    }

    private PressaoVisualizarDTO transformaParaVisualizarDTO(Pressao exame){
        return new PressaoVisualizarDTO(exame.getId(),exame.getSistolica(), exame.getDiastolica(), exame.getObservacoes());
    }

    public PressaoVisualizarDTO visualizarExame(VisualizarExameDTO dto, Usuario usuario) {
        Optional<Pressao> optExame = this.repository.findByIdAndUsuario(dto.getId(), usuario);
        if(optExame.isEmpty()) {
            throw new HealthTrackSystemException("Exame não encontrado");
        }
        return transformaParaVisualizarDTO(optExame.get());
    }
}