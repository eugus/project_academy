package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AuthenticationDTO;
import br.com.versao2.Academia.DTO.LoginResponseDTO;
import br.com.versao2.Academia.DTO.RegisterDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.infra.security.TokenService;
import br.com.versao2.Academia.repository.AlunoRepository;
import br.com.versao2.Academia.service.ForbiddenExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    TokenService service;

    @Autowired
    private AlunoRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.nome(), dto.password());

        try {
            var auth = authenticationManager.authenticate(userNamePassword);
            var token = service.generateToken((Aluno) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha na autenticação, usuário não encontrado");
        }

        //valida as credenciais do usuário

        /*var auth = authenticationManager.authenticate(userNamePassword);
        var token = service.generateToken((Aluno) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

         */


    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){

        //caso exista um usuário com esse nome
        if (repository.findByNome(dto.nome()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Aluno newAluno = new Aluno(dto.nome(), encryptedPassword, dto.role(), dto.cpf(), dto.telefone(), dto.endereco(), dto.dataCadastro());

        repository.save(newAluno);

        return ResponseEntity.ok().build();

    }
}
