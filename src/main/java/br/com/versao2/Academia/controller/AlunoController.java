package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.repository.AlunoRepository;
import br.com.versao2.Academia.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping()
    public ResponseEntity<ResponseMessage> postDto(@RequestBody AlunoDTO dto){
        alunoService.criarAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseMessage("Aluno Criado"));
    }

    @GetMapping()
    public ResponseEntity<List<Aluno>> post(){
        var listAluno  = alunoService.getAluno();
        return ResponseEntity.ok().body(listAluno);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<ResponseMessage> atualizar(@RequestBody AlunoDTO alunoDTO, @PathVariable Long idAluno){
        alunoService.update(alunoDTO, idAluno);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Dados alterados"));
    }


    @GetMapping("/{idAluno}")
    public ResponseEntity<Aluno> getId(@PathVariable Long idAluno){
        Aluno aluno = alunoService.getId(idAluno);
        return ResponseEntity.status(HttpStatus.OK).body(
                 aluno);
    }

    /*@GetMapping("/{idAluno}")
    public ResponseEntity<Aluno> getIdEx(@PathVariable Long idAluno){
        Aluno aluno = alunoService.authenticated();
        Optional<Aluno> aluno2 = Optional.ofNullable(alunoService.getId(idAluno));
        return ResponseEntity.status(HttpStatus.OK).body(
                aluno);
    }

     */

    @DeleteMapping("/{idAluno}")
    public ResponseEntity excluir(@PathVariable Long idAluno){
        alunoService.delete(idAluno);
        return ResponseEntity.status(HttpStatus.OK).body("Exclúido");
    }

}
