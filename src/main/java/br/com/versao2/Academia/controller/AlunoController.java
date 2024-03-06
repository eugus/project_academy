package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @PostMapping()
    public ResponseEntity<AlunoDTO> criar(@RequestBody AlunoDTO alunoDTO){
        alunoService.criarAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTO);
    }
}
