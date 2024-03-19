package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Plano;

public class PlanoDTO {

    private Long codigoPlano;
    private String nomePlano;
    private double valor;

    public PlanoDTO(Plano plano){
        codigoPlano = plano.getCodigoPlano();
        nomePlano = plano.getNomePlano();
        valor = plano.getValor();
    }

    public PlanoDTO(Long codigoPlano, String nomePlano, double valor) {
        this.codigoPlano = codigoPlano;
        this.nomePlano = nomePlano;
        this.valor = valor;
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
}
