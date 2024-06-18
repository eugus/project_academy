package br.com.versao2.Academia.helper;

import br.com.versao2.Academia.entitys.Aluno;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {



    public static ByteArrayInputStream alunosCsv(List<Aluno> cursos){
        final CSVFormat format = CSVFormat.EXCEL.withDelimiter(';');


        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out),format)){
            for (Aluno aluno : cursos){
                List<String> data = Arrays.asList(
                        "idAluno: " + aluno.getIdAluno() + "",
                        "Nome: " + aluno.getNome() + "",
                        "Cpf: " + aluno.getCpf(),
                        "Enedreço: " + aluno.getEndereco(),
                        "Telefone: " + aluno.getTelefone()

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
