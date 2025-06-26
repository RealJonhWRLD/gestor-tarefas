package com.realjonhworld.gestortarefas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private boolean concluida = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Dono da tarefa
}

