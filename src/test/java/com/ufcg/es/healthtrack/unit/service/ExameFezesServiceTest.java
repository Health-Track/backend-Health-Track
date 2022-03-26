package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.fezes.FezesDTO;
import com.ufcg.es.healthtrack.model.exame.ExameFezes;
import com.ufcg.es.healthtrack.repository.FezesRepository;
import com.ufcg.es.healthtrack.service.ExameFezesService;
import com.ufcg.es.healthtrack.unit.TestUtil;
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
public class ExameFezesServiceTest {

    @Mock
    private FezesRepository fezesRepository;

    @InjectMocks
    private ExameFezesService exameFezesService;

    private FezesDTO fezesDTO;
    private ExameFezes exameFezes;
    private Usuario usuario;
    private List<ExameFezes> exameFezesList;

    @Before
    public void setUp() {
        fezesDTO = new FezesDTO("descricao",LocalDateTime.now(),"aspectGeral","protozoarios","helmintos","metodo","observacoes");
        usuario = new Usuario("email@email.com","meu nome","minha Senha");
        exameFezes = new ExameFezes(usuario, "descricao",LocalDateTime.now(),"aspectGeral","protozoarios","helmintos","metodo","observacoes");
        exameFezes.setId(1L);
        exameFezesList = new ArrayList<>();
        exameFezesList.add(exameFezes);
    }

    @Test
    public void adicionaExameDeveExecutarSaveApenasUmVez() {
        exameFezesService.adicionaExame(fezesDTO,usuario);
        verify(fezesRepository, times(TestUtil.UMA_CHAMADA)).save(any());
    }

    @Test
    public void listarTodosPorUsuarioDeveExecutarFindAllByUsuarioApenasUmVez() {
        when(fezesRepository.findAllByUsuario(usuario)).thenReturn(exameFezesList);
        exameFezesService.listarTodosPorUsuario(usuario);
        verify(fezesRepository, times(TestUtil.UMA_CHAMADA)).findAllByUsuario(any());
    }

    @Test
    public void visualizarExameDeveExecutarFindByIdAndUsuarioApenasUmVez() {
        when(fezesRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.of(exameFezes));
        exameFezesService.visualizarExame(1L,usuario);
        verify(fezesRepository, times(TestUtil.UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
    @Test
    public void visualizarExameDeveLancarUmaExcesaoQuandoOExameNaoExistir() {
        when(fezesRepository.findByIdAndUsuario(1L,usuario)).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> exameFezesService.visualizarExame(1L,usuario));
        verify(fezesRepository, times(TestUtil.UMA_CHAMADA)).findByIdAndUsuario(anyLong(),any());
    }
}
