package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.PlanDTO;
import br.com.versao2.Academia.DTO.PlanoDTO;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.infra.security.TokenService;
import br.com.versao2.Academia.repository.PlanoRepository;
import br.com.versao2.Academia.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/plano")

public class PlanoController {
    @Autowired
    private PlanoService planoService;

    private TokenService service;

    @Autowired
    private PlanoRepository planoRepository;

    @PostMapping()
    public ResponseEntity<ResponseMessage> post(@RequestBody @Valid PlanDTO planoDTO) {
        planoService.criarPlano(planoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage("PLANO CRIADO"));
    }

    @GetMapping()
    public ResponseEntity<List<Plano>> post(){
        var listPlano = planoService.get();
        return ResponseEntity.ok().body(listPlano);
    }

    @PutMapping("/{codigoPlano}")
    public ResponseEntity<ResponseMessage> atualizar(@RequestBody PlanDTO planoDTO, @PathVariable  Long codigoPlano){
        planoService.update(planoDTO, codigoPlano);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage("Dados alterados com sucesso"));
    }

    @GetMapping("/{codigoPlano}")
    public ResponseEntity<Plano> getId(@PathVariable Long codigoPlano){
        Plano plano = planoService.getPlano(codigoPlano);
        return ResponseEntity.status(HttpStatus.OK).body(plano);
    }

    @DeleteMapping("/{codigoPlano}")
    public ResponseEntity excluir2(@PathVariable Long codigoPlano){
        planoService.delete(codigoPlano);
        return ResponseEntity.ok().body("ok");
    }


}
