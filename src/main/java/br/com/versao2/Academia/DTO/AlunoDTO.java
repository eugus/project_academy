package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlunoDTO {
    private Long idAluno;
    @NotBlank
    private String nome;
    private String dataCadastro;

    @Column(unique = true) @CPF
    private String cpf;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    @NotBlank
    private String password;

    public UserRole role;

    private Long codigoPlano;

    public AlunoDTO() {
    }

    public AlunoDTO(Aluno aluno) {
        idAluno = aluno.getIdAluno();
        nome = aluno.getNome();
        dataCadastro = aluno.getDataCadastro();
        cpf = aluno.getCpf();
        telefone = aluno.getTelefone();
        endereco = aluno.getEndereco();
        password = aluno.getPassword();
        role = aluno.getRole();
        codigoPlano = aluno.getPlano().getCodigoPlano();
    }

    public AlunoDTO(Long idAluno, String nome, String dataCadastro, String cpf, String telefone, String endereco, String encryptedPassword, UserRole role, Long codigoPlano) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.password = encryptedPassword;
        this.role = role;
        this.codigoPlano = codigoPlano;
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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
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

    public Long getCodigoPlano() {
        return codigoPlano;
    }

    public void setCodigoPlano(Long codigoPlano) {
        this.codigoPlano = codigoPlano;
    }

    public String dataAtual(){

        Date date = new Date();
        SimpleDateFormat sddata = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdhora = new SimpleDateFormat("HH:mm:ss");
        String data = sddata.format(date);
        String hora = sdhora.format(date);

        return data + " " + hora;
    }
}








