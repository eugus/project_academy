package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AlunoService {



    @Autowired
    private AlunoRepository alunoRepository;


    public List<Aluno> getAluno(){
        return alunoRepository.findAll();
    }



    public AlunoDTO criarAluno(AlunoDTO alunoDto){
    Aluno entity = new Aluno();
    entity.setNome(alunoDto.getNome());
    entity.setDataCadastro(LocalDateTime.now());
    entity.setCpf(alunoDto.getCpf());
    entity.setTelefone(alunoDto.getTelefone());
    entity.setEndereco(alunoDto.getEndereco());
    entity.setPassword(alunoDto.getPassword());
    entity.setRole(alunoDto.getRole());

    Plano plano = new Plano();
    plano.setIdPlano(alunoDto.getIdPlano());
    entity.setPlano(plano);

    Aluno dto = alunoRepository.save(entity);
    alunoDto.setIdAluno(dto.getIdAluno());

    return alunoDto;
    }


}
