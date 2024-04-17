package br.com.versao2.Academia.service;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.helper.CSVHelper;
import br.com.versao2.Academia.repository.AlunoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.type.PdfaConformanceEnum;
import net.sf.jasperreports.repo.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportService {

    @Autowired
    private AlunoRepository alunoRepository;

    public byte[] exportReport2() throws IOException, JRException {
        String caminho = "/home/gustavinho3/Documentos/";
        List<Aluno> listAlunos = alunoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:gymstudents.jrxml");
        JasperDesign jasperDesign;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Alunos");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listAlunos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminho + "alunos.pdf");

        Path path = Paths.get(caminho+ "alunos.pdf");
        byte[] arquivo = Files.readAllBytes(path);

        return arquivo;
    }


    public String export( Path path) throws FileNotFoundException, JRException {
         path = Paths.get("/home/gustavinho3/Documentos/alunos.pdf");

        List<Aluno> listAlunos = alunoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:gymstudents.jrxml");
        JasperDesign jasperDesign;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Alunos");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listAlunos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint,   path + "alunos.pdf");
        return "relatório gerado no caminho: "  ;
    }









}
