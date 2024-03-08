package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.repository.AlunoRepository;
import br.com.versao2.Academia.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping()
    public ResponseEntity<AlunoDTO> postDto(@RequestBody AlunoDTO dto){
        alunoService.criarAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping()
    public ResponseEntity<List<Aluno>> post(){
        var retornar  = alunoService.getAluno();
        return ResponseEntity.ok().body(retornar);
    }





}
