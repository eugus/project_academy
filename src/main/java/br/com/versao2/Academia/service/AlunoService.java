package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    plano.setCodigoPlano(alunoDto.getCodigoPlano());
    entity.setPlano(plano);


    Aluno dto = alunoRepository.save(entity);
    alunoDto.setIdAluno(dto.getIdAluno());
    return alunoDto;
    }


    //update
    public AlunoDTO update(AlunoDTO alunoDTO, Long idAluno){
        var encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());

        Aluno entity = alunoRepository.getReferenceById(idAluno);
        entity.setNome(alunoDTO.getNome());
        entity.setCpf(alunoDTO.getCpf());
        entity.setTelefone(alunoDTO.getTelefone());
        entity.setEndereco(alunoDTO.getEndereco());
        entity.setPassword(encryptedPassword);
        alunoDTO.setDataCadastro(entity.getDataCadastro());

        /*Plano plano = new Plano();
        plano.setCodigoPlano(alunoDTO.getCodigoPlano());
        entity.setPlano(plano);

         */

        Aluno dto = alunoRepository.save(entity);
        alunoDTO.setIdAluno(dto.getIdAluno());

        return alunoDTO;
    }


    public Aluno getId(Long idAluno){
        return alunoRepository.findById(idAluno).orElseThrow(
                () -> new IdNotFound("ID não encontrado! ID: " + idAluno));
    }

    public Aluno authenticated() {
        return (Aluno) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



    public void delete(Long idAluno){
        if (alunoRepository.existsById(idAluno)) {
            alunoRepository.deleteById(idAluno);
        }else {
            throw new IdNotFound("ID não encotrado");
        }
    }





}
