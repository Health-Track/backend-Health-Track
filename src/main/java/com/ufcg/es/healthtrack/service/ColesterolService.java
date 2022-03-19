package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.ColesterolDTO;
import com.ufcg.es.healthtrack.model.dto.PressaoDTO;
import com.ufcg.es.healthtrack.model.exame.Colesterol;
import com.ufcg.es.healthtrack.model.exame.Pressao;
import com.ufcg.es.healthtrack.repository.ColesterolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColesterolService {

    @Autowired
    private ColesterolRepository repository;

    public void adicionarDados(ColesterolDTO colesterolDTO , Usuario usuario){
        Colesterol colesterol  = criarColesterol(colesterolDTO, usuario);
        this.repository.save(colesterol);
    }

    private Colesterol criarColesterol(ColesterolDTO colesterolDTO, Usuario usuario) {
        return new Colesterol(usuario, colesterolDTO.getDescricao(), colesterolDTO.getDataMedicao(), colesterolDTO.getColesterolTotal(),
                colesterolDTO.getColesterolHDL(), colesterolDTO.getColesterolNaoHDL(), colesterolDTO.getColesterolLDL(),
                colesterolDTO.getRelacaoTotalHDL());
    }

    public List<ColesterolDTO> listarTodosPorUsuario(Usuario usuarioLogado){
        List<Colesterol> exames = this.repository.findAllByUsuario(usuarioLogado);
        return transformaParaDTO(exames);
    }

    private List<ColesterolDTO> transformaParaDTO(List<Colesterol> exames) {
        List<ColesterolDTO> examesDTO = new ArrayList<>();

        for (Colesterol exame: exames){
            examesDTO.add(transformaParaDTO(exame));
        }
        return examesDTO;
    }

    private ColesterolDTO transformaParaDTO(Colesterol exame){
        return new ColesterolDTO(exame.getDescricao(), exame.getDataMedicao(), exame.getColesterolTotal(),
                                 exame.getColesterolHDL(), exame.getColesterolNaoHDL(), exame.getColesterolLDL(),
                                 exame.getRelacaoTotalHDL());
    }
}
