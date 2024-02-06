package br.com.versao2.Academia.repository;

import br.com.versao2.Academia.entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Users, Long> {
}
