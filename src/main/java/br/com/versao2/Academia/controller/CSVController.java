package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/csv")
public class CSVController {

    @Autowired
    private CSVService csvService;

    @GetMapping("/alunos")
    public ResponseEntity<Resource> getFileAluno(){
        String filename = "alunos.csv";
        //ByteArrayInputStream inputStream = new ByteArrayInputStream(csvService.load());
        org.springframework.core.io.InputStreamResource file = new InputStreamResource(csvService.load());
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "anexo; nome do arquivo= " + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }




}
