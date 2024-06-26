package br.com.versao2.Academia.services;

import br.com.versao2.Academia.DTO.PlanDTO;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.repository.PlanoRepository;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlanoServiceTest {

    @InjectMocks
    private PlanoService planoService;

    @Mock
    private PlanoRepository planoRepository;


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

    //criar plano quando nome não existe


    @Test
    void testShouldCreatePlanoWhenNameNotExist() {

        when(planoRepository.findByNomePlano(planDTO.getNomePlano())).thenReturn(null);

        Plano savedPlano = new Plano();

        when(planoRepository.save(any(Plano.class))).thenReturn(savedPlano);

        PlanDTO createdPlano = planoService.criarPlano(planDTO);

        assertNotNull(createdPlano);

        verify(planoRepository).save(any(Plano.class));
    }

    //lançar exceção quando nome de plano ja existe

    @Test
    void testShouldThrowExceptionWhenNameExist() {

        when(planoRepository.findByNomePlano(planDTO.getNomePlano())).thenReturn(new Plano());

        assertThrows(ExistingEntity.class, () -> planoService.criarPlano(planDTO));
    }

    @Test

    void testShouldListAllPlanos() {
        when(planoRepository.findAll()).thenReturn(Collections.singletonList(plano));
        List<Plano> listPlano =  planoService.get();
        assertNotNull(listPlano);
        assertEquals(1, listPlano.size());

    }

    @Test
    void testShouldGetPlanoWhenIdExist(){
        plano.setCodigoPlano(existingId);
        when(planoRepository.findById(existingId)).thenReturn(Optional.of(plano));

        Plano codPlano = planoService.getIdPlano(existingId);

        assertEquals(existingId, codPlano.getCodigoPlano());

        assertNotNull(codPlano);
        verify(planoRepository).findById(existingId);
    }

    @Test
    void testShouldThrowExceptionWhenIdNotExist() {

        when(planoRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(IdNotFound.class, () -> planoService.getIdPlano(nonExistingId));

        verify(planoRepository).findById(nonExistingId);
    }

    @Test
    void testShouldUpdatePlanoWhenIdExist(){

        when(planoRepository.findById(existingId)).thenReturn(Optional.of(plano));

        when(planoRepository.save(any(Plano.class))).thenReturn(plano);

        planDTO = planoService.update(planDTO, existingId);

        assertNotNull(planDTO);

        verify(planoRepository).findById(existingId);
    }

    @Test
    void testShouldThrowExceptionWhenUpdateIdPlanNotExist() {
        when(planoRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(IdNotFound.class, () -> planoService.update(planDTO, nonExistingId));
    }

    @Test
    void testShouldDeletePlanoWhenIdExist(){

        when(planoRepository.findById(existingId)).thenReturn(Optional.of(plano));
        doNothing().when(planoRepository).deleteById(existingId);

        planoService.delete(existingId);

        verify(planoRepository).deleteById(existingId);

        when(planoRepository.findById(existingId)).thenReturn(Optional.empty());
        Optional<Plano> deletePlano = planoRepository.findById(existingId);
        assertFalse(deletePlano.isPresent());
    }

    @Test
    void testShouldThrowExceptionWhenDeletePlanIdNotExist(){
        doThrow(new IdNotFound(spectedMessageID)).when(planoRepository).deleteById(nonExistingId);
        Exception ex = assertThrows(IdNotFound.class, () -> planoService.delete(nonExistingId));
        String actualMessage = ex.getMessage();
        assertEquals(spectedMessageID, actualMessage);
        verify(planoRepository).deleteById(nonExistingId);
    }

    @Test
    void testGetPage() {
        Pageable pageable = PageRequest.of(0, 10);

        plano.setNomePlano("FIT");

        Page<Plano> page = new PageImpl<>(Collections.singletonList(plano));

        when(planoRepository.findAll(pageable)).thenReturn(page);

        Page<Plano> resultPage = planoService.getPage(pageable);

        assertNotNull(resultPage);

        assertEquals(1, resultPage.getTotalElements());
    }
}
