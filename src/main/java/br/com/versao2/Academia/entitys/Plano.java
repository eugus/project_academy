package br.com.versao2.Academia.entitys;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPlano;
    private String nomePlano;
    private double valor;

    @OneToMany(mappedBy = "plano")
    private List<Aluno> aluno = new ArrayList<>();

    public Plano() {
    }

    public Long getCodigoPlano() {
        return codigoPlano;
    }

    public void setCodigoPlano(Long codigoPlano) {
        this.codigoPlano = codigoPlano;
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

    @Override
    public String toString() {
        return "Plano{" +
                "codigoPlano=" + codigoPlano +
                ", nomePlano='" + nomePlano + '\'' +
                ", valor=" + valor +
                '}';
    }
}


