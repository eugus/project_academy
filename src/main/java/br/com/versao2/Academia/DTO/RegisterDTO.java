package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.UserRole;

public record RegisterDTO(String nome, String password, UserRole role) {
}
