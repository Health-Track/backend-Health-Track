package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaDTO;
import com.ufcg.es.healthtrack.model.exame.ExameUrina;
import com.ufcg.es.healthtrack.repository.UrinaRepository;
import com.ufcg.es.healthtrack.service.ExameUrinaService;
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
public class ExameUrinaServiceTest {

    private final int UMA_CHAMADA = 1;

    @Mock
    private UrinaRepository urinaRepository;

    @InjectMocks
    private ExameUrinaService exameUrinaService;

    private UrinaDTO urinaDTO;
    private ExameUrina exameUrina;
    private Usuario usuario;
    private List<ExameUrina> exameUrinaList;

    @Before
    public void setUp() {
        this.urinaDTO = new UrinaDTO("any",LocalDateTime.now(),"any",1,"any","any","any","any","any","any","any","any",1,1,1,"any","any","any","any","any");
        this.usuario = new Usuario("email@email.com","meu nome","minha Senha");
        this.exameUrina = new ExameUrina(usuario,"any",LocalDateTime.now(),"any",1,"any","any","any","any","any","any","any","any",1,1,1,"any","any","any","any","any");
        this.exameUrina.setId(1L);
        this.exameUrinaList = new ArrayList<>();
        this.exameUrinaList.add(this.exameUrina);
    }

    @Test
    public void adicionaExameDeveExecutarSaveApenasUmVez() {
        exameUrinaService.adicionaExame(urinaDTO,usuario);
        verify(urinaRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void listarTodosPorUsuarioDeveExecutarFindAllByUsuarioApenasUmVez() {
        when(urinaRepository.findAllByUsuario(usuario)).thenReturn(exameUrinaList);
        exameUrinaService.listarTodosPorUsuario(usuario);
        verify(urinaRepository, times(UMA_CHAMADA)).findAllByUsuario(any());
    }

    @Test
    public void visualizarExameDeveExecutarFindByIdAndUsuarioApenasUmVez() {
        when(urinaRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.of(exameUrina));
        exameUrinaService.visualizarExame(1L,usuario);
        verify(urinaRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
    @Test
    public void visualizarExameDeveLancarUmaExcesaoQuandoOExameNaoExistir() {
        when(urinaRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> exameUrinaService.visualizarExame(1L,usuario));
        verify(urinaRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }





}
