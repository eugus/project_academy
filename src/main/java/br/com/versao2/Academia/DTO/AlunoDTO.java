package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Aluno;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AlunoDTO {
    private Long idAluno;
    @NotBlank
    private String nome;
    private LocalDateTime dataCadastro;

    private Long idPlano;



    public AlunoDTO(Aluno aluno){
        idAluno = getIdAluno();
        nome = getNome();
        dataCadastro = getDataCadastro();
        idPlano = getIdPlano();

    }

    public AlunoDTO(Long idAluno, String nome, LocalDateTime dataCadastro) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataCadastro = dataCadastro;

    }

    public Long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Long idPlano) {
        this.idPlano = idPlano;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
