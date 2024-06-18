package br.com.versao2.Academia.services;

import br.com.versao2.Academia.DTO.AlunoDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.repository.AlunoRepository;
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

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    long nonExistingId;
    long existingId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nonExistingId = 200L;
        existingId = 100L;
    }

    @Test
    void testCriarAluno() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setCpf("12345678900");
        when(alunoRepository.findByCpf(alunoDTO.getCpf())).thenReturn(null);
        when(alunoRepository.save(any(Aluno.class))).thenReturn(new Aluno());

        AlunoDTO createdAluno = alunoService.criarAluno(alunoDTO);

        assertNotNull(createdAluno);
    }

    @Test
    void testCriarAlunoExistente() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setCpf("12345678900");
        when(alunoRepository.findByCpf(alunoDTO.getCpf())).thenReturn(new Aluno());

        assertThrows(ExistingEntity.class, () -> alunoService.criarAluno(alunoDTO));
    }

    @Test
    void testUpdate() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setCpf("12345678900");
        alunoDTO.setPassword("password");
        when(alunoRepository.findById(existingId)).thenReturn(Optional.of(new Aluno()));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(new Aluno());

        AlunoDTO updatedAluno = alunoService.update(alunoDTO, 1L);

        assertNotNull(updatedAluno);
    }


    @Test
    void testUpdateIdInexistente(){
        AlunoDTO alunoDTO = new AlunoDTO();
        when(alunoRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(IdNotFound.class, () -> alunoService.update(alunoDTO, nonExistingId));
    }

    @Test
    void testGetId() {
        Aluno aluno = new Aluno();
        aluno.setIdAluno(existingId);
        when(alunoRepository.findById(existingId)).thenReturn(Optional.of(aluno));

        Aluno encontrarAluno = alunoService.getId(existingId);

        assertNotNull(encontrarAluno);
        assertEquals(existingId, encontrarAluno.getIdAluno());
    }

    @Test
    void testGetIdNotFound() {

        //configura o repository para retornar um empty ao buscar um aluno pelo Id 1
        when(alunoRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        //chama o assertthrows para verificar se a exceção IdNotFound é lançada quando o metodo é chamando com o argumento nonExistingId
        assertThrows(IdNotFound.class, () -> alunoService.getId(nonExistingId));
    }


    @Test
    void testGetPage() {
        //objeto pageable com a pagina 0  e tamanho 10
        Pageable pageable = PageRequest.of(0, 10);

        //nova instacia de Aluno
        Aluno aluno = new Aluno();
        aluno.setNome("João");
        aluno.setNome("Maia");

        //intancia de Page com a lista com um único aluno
        Page<Aluno> page = new PageImpl<>(Collections.singletonList(aluno));

        //confiura o repository para retornar o page quando o findAll for chamado
        when(alunoRepository.findAll(pageable)).thenReturn(page);

        //chama o metodo getPage e armazena o resultado em resultPage
        Page<Aluno> resultPage = alunoService.getPage(pageable);

        //verifica se não é nulo
        assertNotNull(resultPage);

        //espera-se um 1 elemento -> verifica o total de elementos(1)
        assertEquals(1, resultPage.getTotalElements());
    }

    // Adicione mais testes para os outros métodos do serviço
}

