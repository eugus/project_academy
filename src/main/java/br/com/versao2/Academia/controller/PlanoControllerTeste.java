package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.PlanoDTO;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plano")

public class PlanoControllerTeste {
    @Autowired
    private PlanoRepository planoRepository;


    @PostMapping()
    public ResponseEntity post(@RequestBody @Valid  PlanoDTO planoDTO){
        Plano plano1 = new Plano(planoDTO);

        planoRepository.save(plano1);
        return ResponseEntity.ok().build();
    }



    @GetMapping()
    public ResponseEntity post(){
        List<Plano> planoList = planoRepository.findAll();
        return ResponseEntity.ok().body(planoList);
    }
}
