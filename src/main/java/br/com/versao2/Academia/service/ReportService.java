package br.com.versao2.Academia.service;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.repository.AlunoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportService {

    private final AlunoRepository alunoRepository;

    public ReportService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public byte[] exportReport2() throws IOException, JRException {
        String caminho = "/home/gustavinho3/Documentos/";
        List<Aluno> listAlunos = alunoRepository.findAll();
        File file = ResourceUtils.getFile("classpath:gymstudents.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Alunos");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listAlunos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminho + "alunos.pdf");

        Path path = Paths.get(caminho+ "alunos.pdf");

        return Files.readAllBytes(path);
    }









}
