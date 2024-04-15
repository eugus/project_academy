package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    @GetMapping("/{format}")
    public ResponseEntity generateReport2(@PathVariable String format) throws FileNotFoundException, JRException {

        var retornar = reportService.exportReport2(format);
        return ResponseEntity.ok().body("Success, " + retornar);
    }


    @GetMapping("/alunos")
    public ResponseEntity<Resource> getFileAluno(){
        String filename = "alunos.pdf";
        org.springframework.core.io.InputStreamResource file = new InputStreamResource(reportService.load());
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "anexo; nome do arquivo= " + filename)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(file);
    }


}
