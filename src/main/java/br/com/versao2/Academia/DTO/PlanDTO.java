package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Plano;
import jakarta.validation.constraints.NotBlank;

public class PlanDTO {

    private Long codigoPlano;

    @NotBlank
    private String nomePlano;


    private double valor;

    public PlanDTO(Plano plano){
        codigoPlano = plano.getCodigoPlano();
        nomePlano = plano.getNomePlano();
        valor = plano.getValor();
    }

    public PlanDTO(Long codigoPlano, String nomePlano, double valor) {
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
