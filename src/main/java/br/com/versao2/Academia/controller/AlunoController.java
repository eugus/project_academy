package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> postDto(@RequestBody @Valid AlunoDTO dto){
        alunoService.criarAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseMessage("Aluno Criado"));
    }


    @Operation(summary = "Lista todos os contextos")
    @GetMapping()
    public ResponseEntity<List<Aluno>> getAluno(){
        var listAluno  = alunoService.getAluno();
        return ResponseEntity.ok().body(listAluno);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<ResponseMessage> update(@RequestBody  AlunoDTO alunoDTO, @PathVariable Long idAluno){
        alunoService.update(alunoDTO, idAluno);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Dados alterados"));
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<Aluno> getId(@PathVariable Long idAluno){
        Aluno aluno = alunoService.getId(idAluno);
        return ResponseEntity.status(HttpStatus.OK).body(
                 aluno);
    }


    @DeleteMapping("/{idAluno}")
    public ResponseEntity<?> delete(@PathVariable Long idAluno){
        alunoService.delete2(idAluno);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado");
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Aluno>> getAlunoPage(Pageable pageable){
        Page<Aluno> page = alunoService.getPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


}
