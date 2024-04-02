package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
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


}
