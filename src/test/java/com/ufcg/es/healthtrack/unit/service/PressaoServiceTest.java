package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoDTO;
import com.ufcg.es.healthtrack.model.exame.Pressao;
import com.ufcg.es.healthtrack.repository.PressaoRepository;
import com.ufcg.es.healthtrack.service.PressaoService;
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
public class PressaoServiceTest {

    private final int UMA_CHAMADA = 1;

    @Mock
    private PressaoRepository pressaoRepository;

    @InjectMocks
    private PressaoService pressaoService;

    private PressaoDTO pressaoDTO;
    private Pressao pressao;
    private Usuario usuario;
    private List<Pressao> pressaoList;

    @Before
    public void setUp() {
        this.pressaoDTO = new PressaoDTO(1,1,"any");
        this.usuario = new Usuario("email@email.com","meu nome","minha Senha");
        this.pressao = new Pressao(usuario,1,1,"any");
        this.pressao.setId(1L);
        this.pressaoList = new ArrayList<>();
        this.pressaoList.add(this.pressao);
    }

    @Test
    public void adicionaExameDeveExecutarSaveApenasUmVez() {
        pressaoService.adicionarMedicao(pressaoDTO,usuario);
        verify(pressaoRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void listarTodosPorUsuarioDeveExecutarFindAllByUsuarioApenasUmVez() {
        when(pressaoRepository.findAllByUsuario(usuario)).thenReturn(pressaoList);
        pressaoService.listarTodosPorUsuario(usuario);
        verify(pressaoRepository, times(UMA_CHAMADA)).findAllByUsuario(any());
    }

    @Test
    public void visualizarExameDeveExecutarFindByIdAndUsuarioApenasUmVez() {
        when(pressaoRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.of(pressao));
        pressaoService.visualizarExame(1L,usuario);
        verify(pressaoRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
    @Test
    public void visualizarExameDeveLancarUmaExcesaoQuandoOExameNaoExistir() {
        when(pressaoRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> pressaoService.visualizarExame(1L,usuario));
        verify(pressaoRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }

}
