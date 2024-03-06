package br.com.versao2.Academia.entitys;

import br.com.versao2.Academia.DTO.PlanoDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlano;
    private String nomePlano;
    private double valor;
    //private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "plano")
    private List<Aluno> aluno = new ArrayList<>();

    public Plano() {
    }

    public Plano(Long idPlano, String nomePlano, double valor) {
        this.idPlano = idPlano;
        this.nomePlano = nomePlano;
        this.valor = valor;

    }

    public Plano(PlanoDTO planoDTO) {
        this.idPlano = getIdPlano();
        this.nomePlano = getNomePlano();
        this.valor = getValor();
    }

    public Long getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(Long idPlano) {
        this.idPlano = idPlano;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }
}


