package br.com.versao2.Academia.service;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.repository.AlunoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportService {

    @Autowired
    private AlunoRepository alunoRepository;


    public String exportReport(String report) throws FileNotFoundException, JRException {
        String path = "/home/gustavinho3/Downloads/";
        List<Aluno> academicos = alunoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:alunos.jrxml");
        JasperDesign jasperDesign;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Alunos");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(academicos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (report.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "\\alunos.html");
        }
        if (report.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\alunos.pdf");

        }
        return "relatório gerado no caminho: " + path;
    }

    public String exportReport2(String report) throws FileNotFoundException, JRException {
        String path = "/home/gustavinho3/Downloads/";
        List<Aluno> teste = alunoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:teste.jrxml");
        JasperDesign jasperDesign;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Teste");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(teste);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (report.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "\\teste.html");
        }
        if (report.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\teste.pdf");

        }
        return "relatório gerado no caminho: " + path;


    }

}
