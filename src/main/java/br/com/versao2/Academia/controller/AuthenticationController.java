package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.DTO.AuthenticationDTO;
import br.com.versao2.Academia.DTO.LoginResponseDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.infra.security.TokenService;
import br.com.versao2.Academia.service.AlunoService;
import br.com.versao2.Academia.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    final
    AlunoService alunoService;

    final
    TokenService service;

    final
    AuthenticationManager authenticationManager;

    final
    AuthorizationService authorizationService;

    public AuthenticationController(AlunoService alunoService, TokenService service, AuthenticationManager authenticationManager, AuthorizationService authorizationService) {
        this.alunoService = alunoService;
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.password());

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
    public ResponseEntity<?> register(@RequestBody @Valid AlunoDTO dto){


        authorizationService.register(dto);

        return ResponseEntity.ok().body("Aluno criado com sucesso! Seja bem-vindo, " + dto.getNome());
    }


    @PostMapping("/register/standard")
    public ResponseEntity<?> registeruserNormal(@RequestBody @Valid AlunoDTO dto){

        authorizationService.registerStandard(dto);

        return ResponseEntity.ok().body("Aluno criado com sucesso! Seja bem-vindo, " + dto.getNome());
    }
}
