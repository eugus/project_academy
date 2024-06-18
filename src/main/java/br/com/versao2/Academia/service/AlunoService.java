package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> getAluno(){
        return alunoRepository.findAll();
    }

    public AlunoDTO criaUsuarioStandard(AlunoDTO alunoDto){

        if (alunoRepository.findByCpf(alunoDto.getCpf()) == null) {

            Aluno entity = new Aluno();
            entity.setNome(alunoDto.getNome());
            entity.setDataCadastro(alunoDto.dataAtual());
            entity.setCpf(alunoDto.getCpf());

            entity.setTelefone(alunoDto.getTelefone());
            entity.setEndereco(alunoDto.getEndereco());
            entity.setPassword(alunoDto.getPassword());
            entity.setRole(alunoDto.getRole());

            Aluno dto = alunoRepository.save(entity);
            alunoDto.setIdAluno(dto.getIdAluno());
        }else {
            throw new ExistingEntity("CPF já foi cadastrado antes");
        }
        return alunoDto;
    }

    public AlunoDTO criarAluno(AlunoDTO alunoDto){

    if (alunoRepository.findByCpf(alunoDto.getCpf()) == null) {

        Aluno entity = new Aluno();
        entity.setNome(alunoDto.getNome());
        entity.setDataCadastro(alunoDto.dataAtual());
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
    }else {
        throw new ExistingEntity("CPF já foi cadastrado antes");
    }
    return alunoDto;
    }


    //update
    public AlunoDTO update(AlunoDTO alunoDTO, Long idAluno){

        try {
            var encryptedPassword = new BCryptPasswordEncoder().encode(alunoDTO.getPassword());

            Aluno entity = alunoRepository.findById(idAluno).get();
            entity.setNome(alunoDTO.getNome());
            entity.setCpf(alunoDTO.getCpf());

            entity.setTelefone(alunoDTO.getTelefone());
            entity.setEndereco(alunoDTO.getEndereco());
            entity.setPassword(encryptedPassword);
            alunoDTO.setDataCadastro(alunoDTO.getDataCadastro());


            Plano plano = new Plano();
            plano.setCodigoPlano(alunoDTO.getCodigoPlano());
            entity.setPlano(plano);



            Aluno dto = alunoRepository.save(entity);
            alunoDTO.setIdAluno(dto.getIdAluno());

        }catch (RuntimeException e){
            throw new IdNotFound("ID Inexistente ou CPF não corresponde a este usuário");
        }

        return alunoDTO;
    }


    public Aluno getId(Long idAluno){
        return alunoRepository.findById(idAluno).orElseThrow(
                () -> new IdNotFound("ID não encontrado! ID: " + idAluno));
    }

    public Aluno authenticated() {
        return (Aluno) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public void delete(Long idAluno) throws IdNotFound{
        if (alunoRepository.existsById(idAluno)) {
            alunoRepository.deleteById(idAluno);
        }else {
            throw new IdNotFound("ID não encotrado");
        }
    }


    public void delete2(Long idAluno) {
        try {
            alunoRepository.deleteById(idAluno);
        }catch (RuntimeException ex) {
            throw new IdNotFound("ID não encotrado");
        }
    }

    public Page<Aluno> getPage(Pageable pageable){
        return alunoRepository.findAll(pageable);
    }
}
