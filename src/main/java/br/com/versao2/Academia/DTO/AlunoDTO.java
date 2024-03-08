package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.UserRole;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AlunoDTO {
    private Long idAluno;
    @NotBlank
    private String nome;
    private LocalDateTime dataCadastro;
    private String cpf;
    private String telefone;
    private String endereco;
    private String password;
    public UserRole role;
    private Long idPlano;

    public AlunoDTO(Aluno aluno){
        idAluno = aluno.getIdAluno();
        nome = aluno.getNome();
        dataCadastro = aluno.getDataCadastro();
        cpf = aluno.getCpf();
        telefone = aluno.getTelefone();
        endereco = aluno.getPassword();
        role = aluno.getRole();
        idPlano = getIdPlano();
    }

    public AlunoDTO(Long idAluno, String nome, LocalDateTime dataCadastro, String cpf, String telefone, String endereco, String password, UserRole role, Long idPlano) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataCadastro = dataCadastro = LocalDateTime.now();
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.password = password;
        this.role = role;
        this.idPlano = idPlano;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
