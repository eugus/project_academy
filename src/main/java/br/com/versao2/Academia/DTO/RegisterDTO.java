package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.entitys.UserRole;

import java.time.LocalDateTime;

public record RegisterDTO(Long idAluno, String nome, String password, UserRole role, String cpf, String telefone, String endereco, LocalDateTime dataCadastro, Plano plano
                        ) {
}
