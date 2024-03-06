package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AlunoService {


    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoDTO criarAluno(AlunoDTO alunoDto){
        Aluno entity = new Aluno();
        entity.setNome(alunoDto.getNome());
        entity.setDataCadastro(LocalDateTime.now());
        Plano plano = new Plano();
        plano.setIdPlano(alunoDto.getIdPlano());
        entity.setPlano(plano);
        Aluno dto = alunoRepository.save(entity);

        return alunoDto;
    }
}
