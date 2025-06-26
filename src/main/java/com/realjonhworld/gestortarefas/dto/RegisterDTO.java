package com.realjonhworld.gestortarefas.dto;

import com.realjonhworld.gestortarefas.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String senha;

    private Role role; // GESTOR ou FUNCIONARIO
}
