package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.dto.hemograma.HemogramaDTO;
import com.ufcg.es.healthtrack.model.exame.Glicemia;
import com.ufcg.es.healthtrack.model.exame.Hemograma;
import com.ufcg.es.healthtrack.repository.HemogramaRepository;
import com.ufcg.es.healthtrack.service.HemogramaService;
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
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class HemogramaServiceTest {

    private final int UMA_CHAMADA = 1;

    @Mock
    private HemogramaRepository hemogramaRepository;

    @InjectMocks
    private HemogramaService hemogramaService;

    private HemogramaDTO hemogramaDTO;
    private Hemograma hemograma;
    private Usuario usuario;
    private List<Hemograma> hemogramaList;

    @Before
    public void setUp() {
        this.hemogramaDTO = new HemogramaDTO("any",LocalDateTime.now(),1,1,1,1,1,1,1,1);
        this.usuario = new Usuario("email@email.com","meu nome","minha Senha");
        this.hemograma = new Hemograma(usuario,"any",LocalDateTime.now(),1,1,1,1,1,1,1,1);
        this.hemograma.setId(1L);
        this.hemogramaList = new ArrayList<>();
        this.hemogramaList.add(this.hemograma);
    }

    @Test
    public void adicionaExameDeveExecutarSaveApenasUmVez() {
        hemogramaService.adicionarExame(hemogramaDTO,usuario);
        verify(hemogramaRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void listarTodosPorUsuarioDeveExecutarFindAllByUsuarioApenasUmVez() {
        when(hemogramaRepository.findAllByUsuario(usuario)).thenReturn(hemogramaList);
        hemogramaService.listarTodosPorUsuario(usuario);
        verify(hemogramaRepository, times(UMA_CHAMADA)).findAllByUsuario(any());
    }

    @Test
    public void visualizarExameDeveExecutarFindByIdAndUsuarioApenasUmVez() {
        when(hemogramaRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.of(hemograma));
        hemogramaService.visualizarExame(1L,usuario);
        verify(hemogramaRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
    @Test
    public void visualizarExameDeveLancarUmaExcesaoQuandoOExameNaoExistir() {
        when(hemogramaRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> hemogramaService.visualizarExame(1L,usuario));
        verify(hemogramaRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }




}
