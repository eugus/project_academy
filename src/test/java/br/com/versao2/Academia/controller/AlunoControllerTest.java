package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AlunoControllerTest {


    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoService alunoService;

    long existingId;
    long nonExistingId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingId = 1L;
        nonExistingId = 100L;


    }

    @Test
    void testPostDto() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");
        when(alunoService.criarAluno(any(AlunoDTO.class))).thenReturn(alunoDTO);

        ResponseEntity<ResponseMessage> response = alunoController.postDto(alunoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //assertEquals("Aluno Criado", response.getBody().getMessage());
    }



    @Test
    void testShouldThrowExceptionWhenCpfExists(){
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setCpf("123456789");
        when(alunoService.criarAluno(any(AlunoDTO.class))).thenThrow(new ExistingEntity("Aluno já existe"));

        Exception exception = assertThrows(ExistingEntity.class, () -> {
            alunoController.postDto(alunoDTO);
        });

        String expectedMessage = "Aluno já existe";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void testShouldBringAListOfStudents() {
        Aluno aluno = new Aluno();
        aluno.setNome("João");
        when(alunoService.getAluno()).thenReturn(Collections.singletonList(aluno));

        ResponseEntity<List<Aluno>> response = alunoController.getAluno();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("João", response.getBody().get(0).getNome());
    }

    @Test
    void testShouldBringAStudentWhenGetIdAluno(){
        Aluno aluno = new Aluno();
        aluno.setIdAluno(existingId);
        when(alunoService.getId(existingId)).thenReturn(aluno);

        ResponseEntity<Aluno> response = alunoController.getId(existingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
