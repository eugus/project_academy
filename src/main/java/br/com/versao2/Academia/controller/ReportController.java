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

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    @GetMapping("/getPdf")
    public ResponseEntity generateReport2() throws IOException, JRException {

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                .body(reportService.exportReport2());
    }

    @GetMapping(value = "/pdfFile")
    public ResponseEntity<?> getPdf() throws IOException {

        Path path = Paths.get("C:\\imagens_sellouspay\\");
        byte[] arquivo = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF).body(arquivo);
    }





}
