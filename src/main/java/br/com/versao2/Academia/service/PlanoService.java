package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.PlanDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    public List<Plano> get(){
       return planoRepository.findAll();
    }

    public PlanDTO criarPlano(PlanDTO planoDTO){
        if (planoRepository.findByNomePlano(planoDTO.getNomePlano()) == null) {

            Plano entity = new Plano();
            entity.setCodigoPlano(planoDTO.getCodigoPlano());
            entity.setNomePlano(planoDTO.getNomePlano());
            entity.setValor(planoDTO.getValor());

            Plano dto = planoRepository.save(entity);
            planoDTO.setCodigoPlano(dto.getCodigoPlano());
        }else {
            throw new ExistingEntity("Plano já existe");
        }
        return planoDTO;
    }

    public PlanDTO update(PlanDTO planoDTO, Long codigoPlano){
        Plano entity = planoRepository.findById(codigoPlano)
                .orElseThrow(() -> new IdNotFound("ID Inexistente"));
        entity.setNomePlano(planoDTO.getNomePlano());
        entity.setValor(planoDTO.getValor());

        Plano dto = planoRepository.save(entity);
        planoDTO.setCodigoPlano(dto.getCodigoPlano());
        return planoDTO;
    }

    public Plano getIdPlano(Long codigoPlano){
        return planoRepository.findById(codigoPlano).orElseThrow(
                ()-> new IdNotFound("ID não encontrado"));
    }

    public void delete(Long codigoPlano){
        if (planoRepository.existsById(codigoPlano)){
            planoRepository.deleteById(codigoPlano);
        }else {
            throw new DataIntegrityViolationException("ERRO");
        }
    }

    public Aluno authenticated() {
        return (Aluno) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public Page<Plano> getPage(Pageable pageable) {
        return planoRepository.findAll(pageable);
    }




}
