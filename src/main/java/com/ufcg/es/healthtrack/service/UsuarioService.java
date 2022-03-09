package com.ufcg.es.healthtrack.service;

import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.UsuarioDTO;
import com.ufcg.es.healthtrack.repository.UsuarioRepository;
import com.ufcg.es.healthtrack.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        verificaDadosUsuarioDTO(usuarioDTO);
        verificaEmailJaCadastrado(usuarioDTO.getEmail());
        this.repository.save(geraUsuario(usuarioDTO));
    }

    public Usuario getUsuario(String email) {
        //TODO verificar se o usuario existe antes de retornar
        return repository.findById(email).get();
    }

    private void verificaDadosUsuarioDTO(UsuarioDTO usuarioDTO) {
        Util.verificaNull(usuarioDTO.getNome(), "Nome");
        Util.verificaStringVazia(usuarioDTO.getNome(), "Nome");

        Util.verificaNull(usuarioDTO.getEmail(), "E-mail");
        Util.verificaStringVazia(usuarioDTO.getEmail(), "E-mail");
        Util.verificaFormatoEmail(usuarioDTO.getEmail());

        Util.verificaNull(usuarioDTO.getSenha(), "Senha");
        Util.verificaStringVazia(usuarioDTO.getSenha(), "Senha");
        Util.verificaFormatoSenha(usuarioDTO.getSenha());
    }


    public boolean validaUsuario(String email, String senha) {
        Optional<Usuario> optionalUsuario = this.repository.findById(email);
        return (optionalUsuario.isPresent() && optionalUsuario.get().verificaSenha(senha));

    }

    public boolean usuarioExiste(String email) {
        return this.repository.findById(email).isPresent();

    }

    private void verificaEmailJaCadastrado(String email) {
        Optional<Usuario> optionalUsuario = this.repository.findById(email);

        if(optionalUsuario.isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
    }

    private Usuario geraUsuario(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.getEmail(), usuarioDTO.getNome(), usuarioDTO.getSenha());
    }

}
