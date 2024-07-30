package br.com.versao2.Academia.DTO;

public class CreateEvent {

    private Long idAluno;
    private String nome;
    private String cpf;

    public CreateEvent(Long idAluno, String nome, String cpf) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
