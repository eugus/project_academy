package br.com.versao2.Academia.DTO;

import br.com.versao2.Academia.entitys.Plano;

public class PlanoDTO {

    private Long idPlano;

    private String nomePlano;

    public PlanoDTO(Plano plano){
        idPlano = plano.getIdPlano();
        nomePlano = plano.getNomePlano();
    }

    public PlanoDTO(Long idPlano, String nomePlano) {
        this.idPlano = idPlano;
        this.nomePlano = nomePlano;
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
