package com.rj.doceria.sonhosDoces.service;

import com.rj.doceria.sonhosDoces.domain.enums.TipoDoce;
import com.rj.doceria.sonhosDoces.domain.model.Doce;
import com.rj.doceria.sonhosDoces.repository.DoceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DoceServiceTest {

    @Autowired
    private DoceRepository doceRepository;

    @AfterEach
    void tearDown() {
        doceRepository.deleteAll();
    }

    @Test
    public void findAll(){
        var doce = new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20);
        doceRepository.save(doce);

        var doceService = new DoceService(doceRepository);
        List<Doce> doceList = doceService.findAll();
        var lastDoce = doceList.get(doceList.size() - 1);

        assertEquals(doce.getNome(), lastDoce.getNome());
        assertEquals(doce.getTipoDoce(), lastDoce.getTipoDoce());
        assertEquals(doce.getQntd(), lastDoce.getQntd());
        assertEquals(doce.getPreco(), lastDoce.getPreco());
    }

    @Test
    public void findById(){
        var doce = doceRepository.save(new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20));

        var doceService = new DoceService(doceRepository);
        var doceFound = doceService.findById(doce.getDoceId());

        assertEquals(doce.getNome(), doceFound.getNome());
        assertEquals(doce.getTipoDoce(), doceFound.getTipoDoce());
        assertEquals(doce.getQntd(), doceFound.getQntd());
        assertEquals(doce.getPreco(), doceFound.getPreco());
        assertEquals(doce.getDoceId(), doceFound.getDoceId());
    }

    @Test
    public void deleteById(){
        var doce = doceRepository.save((new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20)));

        var doceService = new DoceService(doceRepository);
        var doceFound = doceService.findById(doce.getDoceId());

    }

    @Test
    public void save(){
        var doceService = new DoceService(doceRepository);

        var doce = doceRepository.save((new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20)));
        doceService.save(doce);

        assertEquals(1.0, doceRepository.count());
    }

    @Test
    void delete(){
        var doceService = new DoceService(doceRepository);

        var doce = doceRepository.save((new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20)));

        doceService.deleteById(doceService.save(doce).getDoceId());

        assertEquals(0, doceRepository.count());
    }

}
