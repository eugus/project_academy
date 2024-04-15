package br.com.versao2.Academia.helper;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.repository.AlunoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVHelper {



    public static ByteArrayInputStream alunosCsv(List<Aluno> cursos){
        final CSVFormat format = CSVFormat.EXCEL.withDelimiter(';');


        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out),format);){
            for (Aluno aluno : cursos){
                List<String> data = Arrays.asList(
                        String.valueOf("idAluno: " + aluno.getIdAluno()+ ""),
                        "Nome: " + aluno.getNome() + "",
                        String.valueOf("Cpf: " + aluno.getCpf() ),
                        String.valueOf("Enedreço: " + aluno.getEndereco() ),
                        String.valueOf("Telefone: " + aluno.getTelefone())

                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e){
            throw new RuntimeException("falha para importar CSV" + e.getMessage());
        }
    }










}
