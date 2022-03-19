package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.File;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesVisualizarDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaVisualizarDTO;
import com.ufcg.es.healthtrack.model.exame.ExameFezes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExameService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GlicemiaService glicemiaService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ExameFezesService exameFezesService;

    @Autowired
    private HemogramaService hemogramaService;

    public void cadastrarExameGlicemia(GlicemiaDTO glicemiaDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.glicemiaService.adicionarMedicao(glicemiaDTO,usuario);
    }

    public List<GlicemiaDTO> listarTodosExamesGlicemia(String authorizationHeader) {
        return this.glicemiaService.listarTodosPorUsuario(getUsuarioLogado(authorizationHeader));

    }

    public GlicemiaDTO visualizarExameGlicemia(LocalDateTime dataMedicao, String authorizationHeader) {
        return this.glicemiaService.getExamePorData(dataMedicao,getUsuarioLogado(authorizationHeader));
    }



    private Usuario getUsuarioLogado(String authorizationHeader) {
        String emailUsuarioLogado = jwtService.getEmailUsuarioLogado(authorizationHeader);
        return this.usuarioService.getUsuario(emailUsuarioLogado);
    }


    public void cadastrarExameFezes(FezesDTO exameDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.exameFezesService.adicionaExame(exameDTO,usuario);

    }

    public List<FezesVisualizarDTO> listarTodosExamesFezes(String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.exameFezesService.listarTodosPorUsuario(usuario);
    }

    public FezesVisualizarDTO visualizarExameFezes(VisualizarExameDTO dto, String authorizationHeader) {
        return this.exameFezesService.visualizarExame(dto,getUsuarioLogado(authorizationHeader));
    }

    public void cadastrarExameHemograma(HemogramaDTO exame, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.hemogramaService.adicionarExame(exame,usuario);
    }

    public List<HemogramaVisualizarDTO> listarTodosExamesHemograma(String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.hemogramaService.listarTodosPorUsuario(usuario);
    }

    public HemogramaVisualizarDTO visualizarExamesHemograma(VisualizarExameDTO dto, String authorizationHeader) {
        return this.hemogramaService.visualizarExame(dto,getUsuarioLogado(authorizationHeader));
    }
}
