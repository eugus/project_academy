package br.com.versao2.Academia.controller;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.DTO.PlanDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.entitys.ResponseMessage;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.service.PlanoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlanControllerTest {

    @InjectMocks
    private PlanoController planoController;

    @Mock
    private PlanoService planoService;

    long nonExistingId;
    long existingId;
    private Plano plano;
    String spectedMessageID;
    PlanDTO planDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nonExistingId = 200L;
        existingId = 1L;
        plano = new Plano();
        spectedMessageID = "ID não encontrado";
        planDTO = new PlanDTO();
    }


    @Test
    void testShouldCreatePlanWhenNameNoExist() {

        planDTO.setNomePlano("FIT");
        when(planoService.criarPlano(any(PlanDTO.class))).thenReturn(planDTO);

        ResponseEntity<ResponseMessage> response = planoController.post(planDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(planoService).criarPlano(any(PlanDTO.class));
    }

    @Test
    void testShouldThrowExceptionWhenNameExists(){

        planDTO.setNomePlano("FIT");
        when(planoService.criarPlano(any(PlanDTO.class))).thenThrow(new ExistingEntity("Plano já existe"));

        Exception exception = assertThrows(ExistingEntity.class, () ->
                planoController.post(planDTO));


        String expectedMessage = "Plano já existe";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void testShouldBringAListOfPlan() {

        plano.setNomePlano("FAMILY");
        when(planoService.get()).thenReturn(Collections.singletonList(plano));

        ResponseEntity<List<Plano>> response = planoController.getPlano();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("FAMILY", response.getBody().get(0).getNomePlano());
    }


    @Test
    void testShouldBringAStudentWhenGetIdPlan(){

        plano.setCodigoPlano(existingId);
        when(planoService.getIdPlano(existingId)).thenReturn(plano);

        ResponseEntity<Plano> response = planoController.getId(existingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testShouldThrowExceptionWhenGetIdPlanNotFound(){
        plano.setCodigoPlano(nonExistingId);
        when(planoService.getIdPlano(nonExistingId)).thenThrow(new IdNotFound(spectedMessageID));

        Exception exception = assertThrows(IdNotFound.class, () -> planoController.getId(nonExistingId));

        String actualMessage = exception.getMessage();
        assertEquals(spectedMessageID, actualMessage);
    }

    @Test
    void testShouldUpdateWhenGetIdPlan(){
        planDTO.setCodigoPlano(existingId);
        when(planoService.update(planDTO, existingId)).thenReturn(planDTO);

        ResponseEntity<ResponseMessage> updateAluno = planoController.update(planDTO, existingId);
        assertEquals(HttpStatus.OK, updateAluno.getStatusCode());
    }

    @Test
    void testShouldThrowExceptionWhenUpdateIdPlanNotFound(){
        planDTO.setCodigoPlano(nonExistingId);
        when(planoService.update(planDTO, nonExistingId)).thenThrow(new IdNotFound(spectedMessageID));

        Exception exception = assertThrows(IdNotFound.class, () -> planoController.update(planDTO, nonExistingId));

        String actualMessage = exception.getMessage();
        assertEquals(spectedMessageID, actualMessage);
    }

    @Test
    void testShouldDeleteStudentWhenIdPlanExist(){
        planDTO.setCodigoPlano(existingId);
        doNothing().when(planoService).delete(existingId);
        ResponseEntity<?> response = planoController.delete(existingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testShouldThrowExceptionWhenDeleteIdPlanNotExisting(){
        plano.setCodigoPlano(nonExistingId);
        doThrow(new IdNotFound(spectedMessageID)).when(planoService).delete(nonExistingId);
        Exception exception = assertThrows(IdNotFound.class, () -> planoController.delete(nonExistingId));

        String actualMessage = exception.getMessage();
        assertEquals(spectedMessageID, actualMessage);
    }

    @Test
    void testShouldGetPage(){
        Pageable pageable = PageRequest.of(0, 10);
        plano.setNomePlano("FIT");
        Page<Plano> page = new PageImpl<>(Collections.singletonList(plano));
        when(planoService.getPage(pageable)).thenReturn(page);

        ResponseEntity<Page<Plano>> response  = planoController.getPlanoPage(pageable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());

    }



}
