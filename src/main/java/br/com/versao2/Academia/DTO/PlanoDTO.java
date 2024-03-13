package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Plano;

public class PlanoDTO {

    private Long idPlano;
    private String nomePlano;
    private double valor;

    public PlanoDTO(Plano plano){
        idPlano = plano.getIdPlano();
        nomePlano = plano.getNomePlano();
        valor = plano.getValor();
    }

    public PlanoDTO(Long idPlano, String nomePlano, double valor) {
        this.idPlano = idPlano;
        this.nomePlano = nomePlano;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
}
