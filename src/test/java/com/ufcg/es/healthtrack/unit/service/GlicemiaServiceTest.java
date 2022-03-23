package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaDTO;
import com.ufcg.es.healthtrack.model.exame.ExameUrina;
import com.ufcg.es.healthtrack.model.exame.Glicemia;
import com.ufcg.es.healthtrack.repository.GlicemiaRepository;
import com.ufcg.es.healthtrack.service.GlicemiaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GlicemiaServiceTest {

    private final int UMA_CHAMADA = 1;

    @Mock
    private GlicemiaRepository glicemiaRepository;

    @InjectMocks
    private GlicemiaService glicemiaService;

    private GlicemiaDTO glicemiaDTO;
    private Glicemia glicemia;
    private Usuario usuario;
    private List<Glicemia> glicemiaList;

    @Before
    public void setUp() {
        this.glicemiaDTO = new GlicemiaDTO(1,LocalDateTime.now());
        this.usuario = new Usuario("email@email.com","meu nome","minha Senha");
        this.glicemia = new Glicemia(usuario,1,LocalDateTime.now());
        this.glicemiaList = new ArrayList<>();
        this.glicemiaList.add(this.glicemia);
    }

    @Test
    public void adicionaMedicaoDeveExecutarSaveApenasUmVez() {
        glicemiaService.adicionarMedicao(glicemiaDTO,usuario);
        verify(glicemiaRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void adicionaMedicaoDeveLancarUmaExcesaoQuandoJaExistirUmaMedicaoNaMesmaData() {
        when(glicemiaRepository.findByDataMedicaoAndUsuario(glicemia.getDataMedicao(),usuario)).thenReturn(Optional.of(glicemia));
        assertThrows(HealthTrackSystemException.class, () -> glicemiaService.adicionarMedicao(glicemiaDTO,usuario));
    }

    @Test
    public void listarTodosPorUsuarioDeveExecutarFindAllByUsuarioApenasUmVez() {
        when(glicemiaRepository.findAllByUsuario(usuario)).thenReturn(glicemiaList);
        glicemiaService.listarTodosPorUsuario(usuario);
        verify(glicemiaRepository, times(UMA_CHAMADA)).findAllByUsuario(any());
    }

    @Test
    public void visualizarExameDeveExecutarFindByIdAndUsuarioApenasUmVez() {
        when(glicemiaRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.of(glicemia));
        glicemiaService.getExamePorId(1L,usuario);
        verify(glicemiaRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
    @Test
    public void visualizarExameDeveLancarUmaExcesaoQuandoOExameNaoExistir() {
        when(glicemiaRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> glicemiaService.getExamePorId(1L,usuario));
        verify(glicemiaRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }




}
