package com.rj.doceria.sonhosDoces.service;

import com.rj.doceria.sonhosDoces.domain.model.Login;
import com.rj.doceria.sonhosDoces.repository.LoginRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginRepository loginRepository;

    @AfterEach
    void tearDown() {
        loginRepository.deleteAll();
    }

    @Test
    public void login(){
        var loginService = new LoginService(loginRepository);

        var login = new Login(UUID.randomUUID(),"Alex", "alex@teste.com", "1234");
        loginService.login(login);
    }

    @Test
    public void save(){
        var loginService = new LoginService(loginRepository);

        var login = new Login(UUID.randomUUID(),"Alex", "alex@teste.com", "1234");
        loginService.save(login);
    }

    @Test
    public void deleteById(){
        var loginService = new LoginService(loginRepository);

        var login = loginService.save(new Login(UUID.randomUUID(),"Alex", "alex@teste.com", "1234"));
        loginService.save(login);

        loginService.deleteById(login.getLoginId());

        assertEquals(0, loginRepository.count());
    }
}
