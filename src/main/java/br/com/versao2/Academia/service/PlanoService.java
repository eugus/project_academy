package br.com.versao2.Academia.service;

import br.com.versao2.Academia.DTO.PlanDTO;
import br.com.versao2.Academia.entitys.Aluno;
import br.com.versao2.Academia.entitys.Plano;
import br.com.versao2.Academia.exceptions.manipuladas.ExistingEntity;
import br.com.versao2.Academia.exceptions.manipuladas.IdNotFound;
import br.com.versao2.Academia.repository.PlanoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

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

        try {
            Plano entity = planoRepository.findById(codigoPlano).get();
            entity.setNomePlano(planoDTO.getNomePlano());
            entity.setValor(planoDTO.getValor());

            Plano dto = planoRepository.save(entity);
            planoDTO.setCodigoPlano(dto.getCodigoPlano());

        }catch (RuntimeException ex){
            throw new IdNotFound("ID Inexistente");
        }

        return planoDTO;
    }

    public Plano getIdPlano(Long codigoPlano){

        try {
            return planoRepository.findById(codigoPlano).get();

        }catch (Exception e){
            throw new IdNotFound("ID Inexistente");
        }

    }

    public void delete(Long codigoPlano){
        try {
            planoRepository.deleteById(codigoPlano);
        }catch (RuntimeException ex) {
            throw new IdNotFound("ID não encontrado");
        }
    }

    public Aluno authenticated() {
        return (Aluno) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public Page<Plano> getPage(Pageable pageable) {
        return planoRepository.findAll(pageable);
    }




}
