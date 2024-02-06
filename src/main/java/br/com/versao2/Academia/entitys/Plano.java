package br.com.versao2.Academia.entitys;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlano;
    private String nome;
    private double valor;
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "plano")
    private List<Users> users = new ArrayList<>();

    public Plano() {
    }

    public Plano(Long idPlano, String nome, double valor, LocalDateTime dataCadastro) {
        this.idPlano = idPlano;
        this.nome = nome;
        this.valor = valor;
        this.dataCadastro = dataCadastro;
    }

    public Long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Long idPlano) {
        this.idPlano = idPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
