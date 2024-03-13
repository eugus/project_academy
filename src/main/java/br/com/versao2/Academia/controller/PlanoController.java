package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.PlanoDTO;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.repository.PlanoRepository;
import br.com.versao2.Academia.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plano")

public class PlanoController {
    @Autowired
    private PlanoService planoService;


    @PostMapping()
    public ResponseEntity<PlanoDTO> post(@RequestBody @Valid  PlanoDTO planoDTO){
        planoService.criarPlano(planoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoDTO);
    }

    @GetMapping()
    public ResponseEntity<List<Plano>> post(){
        var retornar = planoService.get();
        return ResponseEntity.ok().body(retornar);
    }
}
