package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.ColesterolDTO;
import com.ufcg.es.healthtrack.model.dto.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.dto.PressaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ColesterolService colesterolService;

    @Autowired
    private PressaoService pressaoService;

    public void cadastrarExameGlicemia(GlicemiaDTO glicemiaDTO, String authorizationHeader) {
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.glicemiaService.adicionarMedicao(glicemiaDTO,usuario);
    }

    public List<GlicemiaDTO> listarTodosExamesGlicemia(String authorizationHeader) {
        return this.glicemiaService.listarTodosPorUsuario(getUsuarioLogado(authorizationHeader));

    }

    public GlicemiaDTO visializarExameGlicemia(LocalDateTime dataMedicao, String authorizationHeader) {
        return this.glicemiaService.getExamePorData(dataMedicao,getUsuarioLogado(authorizationHeader));
    }

    private Usuario getUsuarioLogado(String authorizationHeader) {
        String emailUsuarioLogado = jwtService.getEmailUsuarioLogado(authorizationHeader);
        return this.usuarioService.getUsuario(emailUsuarioLogado);
    }

    public void cadastrarExameColesterol(ColesterolDTO colesterolDTO,String authorizationHeader){
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.colesterolService.adicionarDados(colesterolDTO, usuario);
    }

    public List<ColesterolDTO> listarTodosExamesColesterol(String authorizationHeader) {
        return this.colesterolService.listarTodosPorUsuario(getUsuarioLogado(authorizationHeader));
    }

    public void cadastrarExamePressao(PressaoDTO pressaoDTO, String authorizationHeader){
        Usuario usuario = getUsuarioLogado(authorizationHeader);
        this.pressaoService.adicionarMedicao(pressaoDTO, usuario);
    }

    public List<PressaoDTO> listaTodosExamesPressao(String authorizationHeader) {
        return this.pressaoService.listarTodosPorUsuario(getUsuarioLogado(authorizationHeader));
    }
}
