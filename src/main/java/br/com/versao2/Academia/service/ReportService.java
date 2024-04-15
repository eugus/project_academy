package br.com.versao2.Academia.service;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.helper.CSVHelper;
import br.com.versao2.Academia.repository.AlunoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.repo.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportService {

    @Autowired
    private AlunoRepository alunoRepository;


    public String exportReport2(String report) throws FileNotFoundException, JRException {
        String path = "/home/gustavinho3/Documentos/";
        List<Aluno> teste = alunoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:gymstudents.jrxml");
        JasperDesign jasperDesign;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Alunos");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(teste);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (report.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "\\alunos.html");
        }
        if (report.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\alunos.pdf");

        }
        return "relatório gerado no caminho: " + path;
    }


    public ByteArrayInputStream load(){
        List<Aluno> cursos = alunoRepository.findAll();

        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        return in;

    }






}
