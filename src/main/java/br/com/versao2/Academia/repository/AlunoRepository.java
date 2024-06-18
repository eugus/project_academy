package br.com.versao2.Academia.repository;

import br.com.versao2.Academia.entitys.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    UserDetails findByNome(String nome);


    UserDetails findByCpf(String cpf);
}
