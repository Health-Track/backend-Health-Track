package com.ufcg.es.healthtrack.unit.service;

import com.ufcg.es.healthtrack.exception.HealthTrackSystemException;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.AlterarSenhaDTO;
import com.ufcg.es.healthtrack.model.dto.UsuarioDTO;
import com.ufcg.es.healthtrack.repository.UsuarioRepository;
import com.ufcg.es.healthtrack.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    private final int UMA_CHAMADA = 1;
    private final int NENHUMA_CHAMADA = 0;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioDTO usuarioDTOValido;
    private UsuarioDTO usuarioDTONomeNull;
    private UsuarioDTO usuarioDTONomeVazio;
    private UsuarioDTO usuarioDTOEmailNull;
    private UsuarioDTO usuarioDTOEmailVazio;
    private UsuarioDTO usuarioDTOEmailFormatoInvalido;
    private UsuarioDTO usuarioDTOSenhaNull;
    private UsuarioDTO usuarioDTOSenhaVazia;
    private UsuarioDTO usuarioDTOSenhaFormatoInvalido;
    private Usuario usuario;
    private AlterarSenhaDTO senhaDTOValida;
    private AlterarSenhaDTO senhaDTOInvalida;

    @Before
    public void setUp() {
        usuarioDTOValido = new UsuarioDTO("email@email.com","Meu Nome","SenhaComMaisDe8Caracteres");
        usuarioDTONomeNull = new UsuarioDTO("email@email.com",null,"SenhaComMaisDe8Caracteres");
        usuarioDTONomeVazio = new UsuarioDTO("email@email.com","","SenhaComMaisDe8Caracteres");
        usuarioDTOEmailNull = new UsuarioDTO(null,"Meu Nome","SenhaComMaisDe8Caracteres");
        usuarioDTOEmailVazio = new UsuarioDTO("","Meu Nome","SenhaComMaisDe8Caracteres");
        usuarioDTOEmailFormatoInvalido = new UsuarioDTO("email##","Meu Nome","SenhaComMaisDe8Caracteres");
        usuarioDTOSenhaNull = new UsuarioDTO("email@email.com","Meu Nome",null);
        usuarioDTOSenhaVazia = new UsuarioDTO("email@email.com","Meu Nome","");
        usuarioDTOSenhaFormatoInvalido = new UsuarioDTO("email@email.com","Meu Nome","-Q8char");
        usuario = new Usuario("email@email.com","Meu Nome","SenhaComMaisDe8Caracteres");
        senhaDTOValida = new AlterarSenhaDTO("SenhaComMaisDe8Caracteres", "NovaSenhaComMaisDe8Caracteres");
        senhaDTOInvalida = new AlterarSenhaDTO("SenhaComDiferente", "NovaSenhaComMaisDe8Caracteres");
    }

    @Test
    public void cadastrarUsuarioValido() {
        usuarioService.cadastrarUsuario(usuarioDTOValido);
        verify(usuarioRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTONomeNull));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTONomeVazio));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOEmailNull));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioEmailVazio() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOEmailVazio));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioEmailFormatoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOEmailFormatoInvalido));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioSenhaNull() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOSenhaNull));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioSenhaVazia() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOSenhaVazia));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioSenhaFormatoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOSenhaFormatoInvalido));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void cadastrarUsuarioJaCadastrado() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrarUsuario(usuarioDTOValido));
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void getUsuarioCadastrado() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        usuarioService.getUsuario(usuarioDTOValido.getEmail());
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
    }

    @Test
    public void getUsuarioNãoCadastrado() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.empty());
        assertThrows(HealthTrackSystemException.class, () -> usuarioService.getUsuario(usuarioDTOValido.getEmail()));
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
    }

    @Test
    public void validaUsuarioValido() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        assertTrue(usuarioService.validaUsuario(usuarioDTOValido.getEmail(),usuarioDTOValido.getSenha()));
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
    }

    @Test
    public void validaUsuarioInvalido() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        assertFalse(usuarioService.validaUsuario(usuarioDTOValido.getEmail(),"SenhaDiferente"));
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
    }

    @Test
    public void usuarioExisteTrue() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        assertTrue(usuarioService.usuarioExiste(usuarioDTOValido.getEmail()));
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
    }

    @Test
    public void usuarioExisteFalse() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.empty());
        assertFalse(usuarioService.usuarioExiste(usuarioDTOValido.getEmail()));
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
    }

    @Test
    public void alterarSenhaValida() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        usuarioService.alterarSenhaUsuario(senhaDTOValida,usuarioDTOValido.getEmail());
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
        verify(usuarioRepository, times(UMA_CHAMADA)).save(any());
    }

    @Test
    public void alterarSenhaInvalida() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(HealthTrackSystemException.class, () -> usuarioService.alterarSenhaUsuario(senhaDTOInvalida,usuarioDTOValido.getEmail()));
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
        verify(usuarioRepository, times(NENHUMA_CHAMADA)).save(any());
    }

    @Test
    public void removerUsuario() {
        when(usuarioRepository.findById(usuarioDTOValido.getEmail())).thenReturn(Optional.of(usuario));
        usuarioService.removerUsuario(usuarioDTOValido.getEmail());
        verify(usuarioRepository, times(UMA_CHAMADA)).findById(any());
        verify(usuarioRepository, times(UMA_CHAMADA)).delete(any());
    }

}
