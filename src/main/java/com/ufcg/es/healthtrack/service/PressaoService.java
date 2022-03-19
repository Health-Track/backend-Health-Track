package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.Usuario;

import com.ufcg.es.healthtrack.model.dto.PressaoDTO;
import com.ufcg.es.healthtrack.model.exame.Pressao;
import com.ufcg.es.healthtrack.repository.PressaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<PressaoDTO> listarTodosPorUsuario(Usuario usuarioLogado) {
        List<Pressao> exames = this.repository.findAllByUsuario(usuarioLogado);
        return transformaParaDTO(exames);
    }

    private List<PressaoDTO> transformaParaDTO(List<Pressao> exames) {
        List<PressaoDTO> examesDTO = new ArrayList<>();

        for (Pressao exame : exames) {
            examesDTO.add(transformaParaDTO(exame));
        }
        return examesDTO;
    }

    private PressaoDTO transformaParaDTO(Pressao exame){
        return new PressaoDTO(exame.getSistolica(), exame.getDiastolica(), exame.getObservacoes());
    }
}