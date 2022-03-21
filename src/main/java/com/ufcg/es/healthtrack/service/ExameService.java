package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.File;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolDTO;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaVisualizarDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoDTO;
import com.ufcg.es.healthtrack.model.dto.VisualizarExameDTO;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolVisualizarDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesDTO;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesVisualizarDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaVisualizarDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoVisualizarDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaVisualizarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private ColesterolService colesterolService;

    @Autowired
    private PressaoService pressaoService;

    @Autowired
    private ExameUrinaService exameUrinaService;


    public void cadastrarExameGlicemia(GlicemiaDTO glicemiaDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.glicemiaService.adicionarMedicao(glicemiaDTO,usuario);
    }

    public List<GlicemiaVisualizarDTO> listarTodosExamesGlicemia(String authorizationHeader) {
        return this.glicemiaService.listarTodosPorUsuario(getUsuarioLogado(authorizationHeader));

    }

    public GlicemiaVisualizarDTO visualizarExameGlicemia(long id, String authorizationHeader) {
        return this.glicemiaService.getExamePorId(id,getUsuarioLogado(authorizationHeader));
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

    public FezesVisualizarDTO visualizarExameFezes(long id, String authorizationHeader) {
        return this.exameFezesService.visualizarExame(id,getUsuarioLogado(authorizationHeader));
    }

    public void cadastrarExameHemograma(HemogramaDTO exame, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.hemogramaService.adicionarExame(exame,usuario);
    }

    public List<HemogramaVisualizarDTO> listarTodosExamesHemograma(String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.hemogramaService.listarTodosPorUsuario(usuario);
    }

    public HemogramaVisualizarDTO visualizarExamesHemograma(long id, String authorizationHeader) {
        return this.hemogramaService.visualizarExame(id,getUsuarioLogado(authorizationHeader));
    }

    public void cadastrarExameColesterol(ColesterolDTO colesterolDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.colesterolService.adicionarDados(colesterolDTO,usuario);
    }

    public List<ColesterolVisualizarDTO> listarTodosExamesColesterol(String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.colesterolService.listarTodosPorUsuario(usuario);
    }

    public void cadastrarExamePressao(PressaoDTO pressaoDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.pressaoService.adicionarMedicao(pressaoDTO,usuario);
    }

    public List<PressaoVisualizarDTO> listaTodosExamesPressao(String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.pressaoService.listarTodosPorUsuario(usuario);
    }

    public ColesterolVisualizarDTO visualizarExameColesterol(long id, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.colesterolService.visualizarExame(id, usuario);
    }

    public void uploadFile(MultipartFile multipartfile, String authorizationHeader) throws IOException {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.fileService.uploadFile(multipartfile,usuario);
    }


    public File downloadFile(long id, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.fileService.getFile(id);
    }

    public PressaoVisualizarDTO visualizarExamePressao(long id, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.pressaoService.visualizarExame(id,usuario);
    }

    public void cadastrarExameUrina(UrinaDTO urinaDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.exameUrinaService.adicionaExame(urinaDTO,usuario);
    }

    public List<UrinaVisualizarDTO> listarTodosExamesUrina(String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.exameUrinaService.listarTodosPorUsuario(usuario);
    }

    public UrinaVisualizarDTO visualizarExameUrina(long id, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        return this.exameUrinaService.visualizarExame(id,usuario);
    }
}
