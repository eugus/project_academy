package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AuthenticationDTO;
import br.com.versao2.Academia.DTO.LoginResponseDTO;
import br.com.versao2.Academia.DTO.RegisterDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.infra.security.TokenService;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        var auth = authenticationManager.authenticate(userNamePassword);

        var token = service.generateToken((Aluno) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){
        if (repository.findByNome(dto.nome()) != null) return ResponseEntity.badRequest().build();
        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Aluno newAluno = new Aluno(dto.nome(), encryptedPassword, dto.role(), dto.cpf(), dto.telefone(), dto.endereco());

        repository.save(newAluno);

        return ResponseEntity.ok().build();

    }
}
