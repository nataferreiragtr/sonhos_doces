package com.rj.doceria.sonhosDoces.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rj.doceria.sonhosDoces.domain.model.Login;
import com.rj.doceria.sonhosDoces.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    public void createLogin() throws Exception {
        var login = new Login(UUID.randomUUID(),"Alex", "alex@teste", "1234");

        Mockito.when(loginService.save(any(Login.class))).thenReturn(login);

        var mapper = new ObjectMapper();
        var jsonLogin = mapper.writeValueAsString(login);

        var resultActions = mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonLogin));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(jsonLogin))
                .andExpect(jsonPath("$.nome").value("Alex"));
    }

    @Test
    void deleteLogin() throws Exception {
        var login = new Login(UUID.randomUUID(),"Alex", "alex@teste.com", "1234");
        var loginServiceMock = mock(LoginService.class);
        doNothing().when(loginServiceMock).deleteById(any(UUID.class));

        mockMvc.perform(delete("/auth/" + login.getLoginId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

}
