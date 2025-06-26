package com.realjonhworld.gestortarefas.controller;
import lombok.RequiredArgsConstructor;
import com.realjonhworld.gestortarefas.service.UsuarioService;
import com.realjonhworld.gestortarefas.dto.RegisterDTO;
import com.realjonhworld.gestortarefas.model.Usuario;
import com.realjonhworld.gestortarefas.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.realjonhworld.gestortarefas.dto.LoginDTO;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody RegisterDTO dto) {
        Usuario usuario = usuarioService.cadastrar(dto);
        usuario.setSenha("*****");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtUtil.generateToken(usuario);
        return ResponseEntity.ok(token);
    }

}



