package br.com.versao2.Academia.repository;

import br.com.versao2.Academia.entitys.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {

    Plano findByNomePlano(String nomePlano);
}
