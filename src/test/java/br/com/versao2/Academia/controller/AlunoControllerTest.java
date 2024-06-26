package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AlunoControllerTest {


    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoService alunoService;

    long existingId;
    long nonExistingId;
    private AlunoDTO alunoDTO;
    String spectedMessageID;
    private Aluno aluno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingId = 1L;
        nonExistingId = 100L;
        alunoDTO = new AlunoDTO();
        spectedMessageID = "ID inexistente";
        aluno = new Aluno();
    }

    @Test
    void testShouldCreateStudentWhenCpfNotExist() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");
        when(alunoService.criarAluno(any(AlunoDTO.class))).thenReturn(alunoDTO);

        ResponseEntity<ResponseMessage> response = alunoController.postDto(alunoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testShouldThrowExceptionWhenCpfExists(){
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setCpf("123456789");
        when(alunoService.criarAluno(any(AlunoDTO.class))).thenThrow(new ExistingEntity("Aluno já existe"));

        Exception exception = assertThrows(ExistingEntity.class, () ->
            alunoController.postDto(alunoDTO));


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
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
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

    @Test
    void testShouldThrowExceptionWhenGetIdAlunoNotFound(){
        aluno.setIdAluno(nonExistingId);
        when(alunoService.getId(nonExistingId)).thenThrow(new IdNotFound(spectedMessageID));

        Exception exception = assertThrows(IdNotFound.class, () -> alunoController.getId(nonExistingId));

        String actualMessage = exception.getMessage();
        assertEquals(spectedMessageID, actualMessage);
    }

    @Test
    void testShouldUpdateWhenGetIdAluno(){
        alunoDTO.setIdAluno(existingId);
        when(alunoService.update(alunoDTO, existingId)).thenReturn(alunoDTO);

        ResponseEntity<ResponseMessage> updateAluno = alunoController.update(alunoDTO, existingId);
        assertEquals(HttpStatus.OK, updateAluno.getStatusCode());
    }

    @Test
    void testShouldThrowExceptionWhenUpdateIdAlunoNotFound(){
        alunoDTO.setIdAluno(nonExistingId);
        when(alunoService.update(alunoDTO, nonExistingId)).thenThrow(new IdNotFound(spectedMessageID));

        Exception exception = assertThrows(IdNotFound.class, () -> alunoController.update(alunoDTO, nonExistingId));

        String actualMessage = exception.getMessage();
        assertEquals(spectedMessageID, actualMessage);
    }

    @Test
    void testShouldThrowExceptionWhenDeleteIdStudentNotExisting(){
        alunoDTO.setIdAluno(nonExistingId);
        doThrow(new IdNotFound(spectedMessageID)).when(alunoService).delete2(nonExistingId);
        Exception exception = assertThrows(IdNotFound.class, () -> alunoController.delete(nonExistingId));

        String actualMessage = exception.getMessage();
        assertEquals(spectedMessageID, actualMessage);
    }

    @Test
    void testShouldDeleteStudentWhenIdAlunoExist(){
        alunoDTO.setIdAluno(existingId);
        doNothing().when(alunoService).delete2(existingId);
        ResponseEntity<?> response = alunoController.delete(existingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testShouldGetPage(){
        Pageable pageable = PageRequest.of(0, 10);
        aluno.setNome("Luis");
        Page<Aluno> page = new PageImpl<>(Collections.singletonList(aluno));
        when(alunoService.getPage(pageable)).thenReturn(page);

        ResponseEntity<Page<Aluno>> response  = alunoController.getAlunoPage(pageable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());

    }

}
