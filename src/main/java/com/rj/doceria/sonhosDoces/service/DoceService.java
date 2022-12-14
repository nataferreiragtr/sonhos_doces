package com.rj.doceria.sonhosDoces.service;

import com.rj.doceria.sonhosDoces.domain.model.Doce;
import com.rj.doceria.sonhosDoces.repository.DoceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoceService {
    private final DoceRepository doceRepository;

    public DoceService(DoceRepository doceRepository) {
        this.doceRepository = doceRepository;
    }

    public Doce save(Doce doce){
        return doceRepository.save(doce);
    }

    public List<Doce> findAll(){
        return doceRepository.findAll();
    }

    public Doce findById(UUID id) {
        return doceRepository.findById(id).orElse(new Doce());
    }

    public void deleteById(UUID id){
        doceRepository.deleteById(id);
    }

}
