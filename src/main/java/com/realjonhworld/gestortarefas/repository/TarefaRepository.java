package com.realjonhworld.gestortarefas.repository;

import com.realjonhworld.gestortarefas.model.Tarefa;
import com.realjonhworld.gestortarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(Usuario usuario);
}


