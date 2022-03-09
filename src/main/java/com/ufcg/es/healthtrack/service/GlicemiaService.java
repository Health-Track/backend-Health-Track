package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.DataMedicaoJaCadastradaException;
import com.ufcg.es.healthtrack.exception.ExameNaoEncontradoException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.GlicemiaDTO;
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

    public void adicionarMedicao(Glicemia glicemia) {
        verificaDataMedicao(glicemia.getDataMedicao(),glicemia.getUsuario());
        this.repository.save(glicemia);
    }


    public List<GlicemiaDTO> listarTodosPorUsuario(Usuario usuarioLogado) {
        List<Glicemia> exames = this.repository.findAllByUsuario(usuarioLogado);
        return transformaParaDTO(exames);
    }

    public GlicemiaDTO getExamePorData(LocalDateTime dataMedicao, Usuario usuario) {
        Optional<Glicemia> optGlicemia = this.repository.findByDataMedicaoAndUsuario(dataMedicao,usuario);
        if(optGlicemia.isEmpty()) {
            throw new ExameNaoEncontradoException("O exame informado não foi encontrado.");
        }
        return transformaParaDTO(optGlicemia.get());
    }

    private List<GlicemiaDTO> transformaParaDTO(List<Glicemia> exames) {
        List<GlicemiaDTO> examesDTO = new ArrayList<>();

        for(Glicemia exame: exames) {
            examesDTO.add(transformaParaDTO(exame));
        }

        return examesDTO;
    }

    private GlicemiaDTO transformaParaDTO(Glicemia exame) {
        return new GlicemiaDTO(exame.getMedicao(), exame.getDataMedicao());
    }

    private void verificaDataMedicao(LocalDateTime dataMedicao, Usuario usuario){
        Optional<Glicemia> optGlicemia = this.repository.findByDataMedicaoAndUsuario(dataMedicao,usuario);
        if(optGlicemia.isPresent()) {
            throw new DataMedicaoJaCadastradaException("Já existe uma medição de glicemia no momento informado");
        }
    }


}
