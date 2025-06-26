package com.realjonhworld.gestortarefas.service;

import com.realjonhworld.gestortarefas.dto.RegisterDTO;
import com.realjonhworld.gestortarefas.model.Role;
import com.realjonhworld.gestortarefas.model.Usuario;
import com.realjonhworld.gestortarefas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario cadastrar(RegisterDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(Role.GESTOR);
        return usuarioRepository.save(usuario);
    }

}


