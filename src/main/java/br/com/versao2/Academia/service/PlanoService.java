package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.PlanoDTO;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public List<Plano> get(){
       return planoRepository.findAll();
    }


    public PlanoDTO criarPlano(PlanoDTO planoDTO){
        Plano entity = new Plano();
        entity.setIdPlano(planoDTO.getIdPlano());
        entity.setNomePlano(planoDTO.getNomePlano());


        Plano dto = planoRepository.save(entity);
        planoDTO.setIdPlano(dto.getIdPlano());
        return planoDTO;
    }

}
