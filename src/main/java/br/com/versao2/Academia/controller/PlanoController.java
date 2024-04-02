package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.PlanDTO;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plano")

public class PlanoController {
    @Autowired
    private PlanoService planoService;


    @PostMapping()
    public ResponseEntity<ResponseMessage> post(@RequestBody @Valid PlanDTO planoDTO) {
        planoService.criarPlano(planoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage("PLANO CRIADO"));
    }

    @GetMapping()
    public ResponseEntity<List<Plano>> getPlano(){
        var listPlano = planoService.get();
        return ResponseEntity.ok().body(listPlano);
    }

    @PutMapping("/{codigoPlano}")
    public ResponseEntity<ResponseMessage> update(@RequestBody @Valid PlanDTO planoDTO, @PathVariable  Long codigoPlano){
        planoService.update(planoDTO, codigoPlano);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage("Dados alterados com sucesso"));
    }

    @GetMapping("/{codigoPlano}")
    public ResponseEntity<Plano> getId(@PathVariable Long codigoPlano){
        Plano plano = planoService.getIdPlano(codigoPlano);
        return ResponseEntity.status(HttpStatus.OK).body(plano);
    }

    @DeleteMapping("/{codigoPlano}")
    public ResponseEntity delete(@PathVariable Long codigoPlano){
        planoService.delete(codigoPlano);
        return ResponseEntity.ok().body("Plano deletado com sucesso");
    }


}
