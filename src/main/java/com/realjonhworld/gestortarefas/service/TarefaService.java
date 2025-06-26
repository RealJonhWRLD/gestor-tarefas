package com.realjonhworld.gestortarefas.service;

import com.realjonhworld.gestortarefas.model.Tarefa;
import com.realjonhworld.gestortarefas.model.Usuario;
import com.realjonhworld.gestortarefas.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa, Usuario usuario) {
        tarefa.setUsuario(usuario);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasDoUsuario(Usuario usuario) {
        return tarefaRepository.findByUsuario(usuario);
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public void deletarTarefa(Tarefa tarefa) {
        tarefaRepository.delete(tarefa);
    }

    public Tarefa atualizarTarefa(Tarefa tarefaExistente, Tarefa novaTarefa) {
        tarefaExistente.setTitulo(novaTarefa.getTitulo());
        tarefaExistente.setDescricao(novaTarefa.getDescricao());
        tarefaExistente.setConcluida(novaTarefa.isConcluida());
        return tarefaRepository.save(tarefaExistente);
    }
}

