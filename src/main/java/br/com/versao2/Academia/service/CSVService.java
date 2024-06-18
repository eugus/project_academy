package br.com.versao2.Academia.service;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.helper.CSVHelper;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service

public class CSVService {


    private final AlunoRepository alunoRepository;

    public CSVService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public ByteArrayInputStream load(){
        List<Aluno> cursos = alunoRepository.findAll();

        return CSVHelper.alunosCsv(cursos);

    }


}
