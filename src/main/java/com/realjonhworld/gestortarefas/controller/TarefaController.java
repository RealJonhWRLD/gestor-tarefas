package com.realjonhworld.gestortarefas.controller;

import com.realjonhworld.gestortarefas.model.Tarefa;
import com.realjonhworld.gestortarefas.model.Usuario;
import com.realjonhworld.gestortarefas.security.UserDetailsImpl;
import com.realjonhworld.gestortarefas.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Usuario usuario = userDetails.getUsuario();
        Tarefa nova = tarefaService.criarTarefa(tarefa, usuario);
        return ResponseEntity.ok(nova);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Usuario usuario = userDetails.getUsuario();
        return ResponseEntity.ok(tarefaService.listarTarefasDoUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa atualizada, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Usuario usuario = userDetails.getUsuario();

        return tarefaService.buscarPorId(id)
                .filter(t -> t.getUsuario().getId().equals(usuario.getId()))
                .map(t -> ResponseEntity.ok(tarefaService.atualizarTarefa(t, atualizada)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Usuario usuario = userDetails.getUsuario();

        return tarefaService.buscarPorId(id)
                .filter(t -> t.getUsuario().getId().equals(usuario.getId()))
                .map(t -> {
                    tarefaService.deletarTarefa(t);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

