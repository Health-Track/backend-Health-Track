package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.exame.Glicemia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GlicemiaService glicemiaService;

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
}
