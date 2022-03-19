package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolDTO;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.Colesterol;
import com.ufcg.es.healthtrack.repository.ColesterolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ColesterolVisualizarDTO> listarTodosPorUsuario(Usuario usuarioLogado){
        List<Colesterol> exames = this.repository.findAllByUsuario(usuarioLogado);
        return transformaParaDTO(exames);
    }

    private List<ColesterolVisualizarDTO> transformaParaDTO(List<Colesterol> exames) {
        List<ColesterolVisualizarDTO> examesDTO = new ArrayList<>();

        for (Colesterol exame: exames){
            examesDTO.add(transformaParaDTO(exame));
        }
        return examesDTO;
    }

    private ColesterolVisualizarDTO transformaParaDTO(Colesterol exame){
        return new ColesterolVisualizarDTO(exame.getId(), exame.getDataMedicao(),exame.getDescricao(), exame.getColesterolTotal(),
                                 exame.getColesterolHDL(), exame.getColesterolNaoHDL(), exame.getColesterolLDL(),
                                 exame.getRelacaoTotalHDL());
    }

    public ColesterolVisualizarDTO visualizarExame(VisualizarExameDTO dto, Usuario usuario) {
        Optional<Colesterol> optColesterol = this.repository.findByIdAndUsuario(dto.getId(),usuario);
        if(optColesterol.isEmpty()) {
            throw new HealthTrackSystemException("Exame não encontrado");
        }
        return transformaParaDTO(optColesterol.get());
    }
}
