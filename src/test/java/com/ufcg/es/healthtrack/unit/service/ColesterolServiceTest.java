package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.colesterol.ColesterolDTO;
import com.ufcg.es.healthtrack.model.exame.Colesterol;
import com.ufcg.es.healthtrack.repository.ColesterolRepository;
import com.ufcg.es.healthtrack.service.ColesterolService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ColesterolServiceTest {

    private final int UMA_CHAMADA = 1;

    @Mock
    private ColesterolRepository colesterolRepository;

    @InjectMocks
    private ColesterolService colesterolService;

    private ColesterolDTO colesterolDTO;
    private Colesterol colesterol;
    private Usuario usuario;
    private List<Colesterol> colesterolList;

    @Before
    public void setUp() {
        this.colesterolDTO = new ColesterolDTO("descricao", LocalDateTime.now(),1,1,1,1,1);
        this.usuario = new Usuario("email@email.com","meu nome","minha Senha");
        this.colesterol = new Colesterol(usuario,"descricao", LocalDateTime.now(),1,1,1,1,1);
        this.colesterolList = new ArrayList<>();
        this.colesterolList.add(this.colesterol);
    }

    @Test
    public void adicionaExameDeveExecutarSaveApenasUmVez() {
        colesterolService.adicionarDados(colesterolDTO,usuario);
        verify(colesterolRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void listarTodosPorUsuarioDeveExecutarFindAllByUsuarioApenasUmVez() {
        when(colesterolRepository.findAllByUsuario(usuario)).thenReturn(colesterolList);
        colesterolService.listarTodosPorUsuario(usuario);
        verify(colesterolRepository, times(UMA_CHAMADA)).findAllByUsuario(any());
    }

    @Test
    public void visualizarExameDeveExecutarFindByIdAndUsuarioApenasUmVez() {
        when(colesterolRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.of(colesterol));
        colesterolService.visualizarExame(1L,usuario);
        verify(colesterolRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
    @Test
    public void visualizarExameDeveLancarUmaExcesaoQuandoOExameNaoExistir() {
        when(colesterolRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> colesterolService.visualizarExame(1L,usuario));
        verify(colesterolRepository, times(UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }





}
