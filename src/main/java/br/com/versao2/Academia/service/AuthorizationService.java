package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    final
    AlunoRepository alunoRepository;
    final
    AlunoService alunoService;

    public AuthorizationService(AlunoRepository alunoRepository, AlunoService alunoService) {
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return alunoRepository.findByCpf(username);
    }

    public void register(AlunoDTO dto){

        verificarCpf(dto);
        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());


        AlunoDTO newAlunoDto = new AlunoDTO(dto.getIdAluno(), dto.getNome(),
                dto.getDataCadastro(), dto.getCpf(), dto.getTelefone(),
                dto.getEndereco(), encryptedPassword,dto.getRole(), dto.getCodigoPlano());

        alunoService.criarAluno(newAlunoDto);
    }

    public void registerStandard(AlunoDTO dto){

        verificarCpf(dto);
        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());


        AlunoDTO newAlunoDto = new AlunoDTO(dto.getIdAluno(), dto.getNome(),
                dto.getDataCadastro(), dto.getCpf(), dto.getTelefone(),
                dto.getEndereco(), encryptedPassword,dto.getRole(), dto.getCodigoPlano());

         alunoService.criaUsuarioStandard(newAlunoDto);
    }

    public void verificarCpf(AlunoDTO dto){
        if (alunoRepository.findByCpf(dto.getCpf()) != null){
            throw new ExistingEntity("Usuário ja cadastrado");
        }
    }
}
