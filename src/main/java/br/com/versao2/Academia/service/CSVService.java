package br.com.versao2.Academia.service;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.helper.CSVHelper;
import br.com.versao2.Academia.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service

public class CSVService {


    @Autowired
    private AlunoRepository alunoRepository;

    public ByteArrayInputStream load(){
        List<Aluno> cursos = alunoRepository.findAll();

        ByteArrayInputStream in = CSVHelper.alunosCsv(cursos);
        return in;

    }


}
