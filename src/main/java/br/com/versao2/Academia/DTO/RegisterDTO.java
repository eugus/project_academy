package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.UserRole;

import java.time.LocalDateTime;

public record RegisterDTO(String nome, String password,UserRole role, String cpf, String telefone, String endereco
                        ) {
}
