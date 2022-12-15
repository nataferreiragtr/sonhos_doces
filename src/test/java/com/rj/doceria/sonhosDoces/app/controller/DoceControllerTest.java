package com.rj.doceria.sonhosDoces.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rj.doceria.sonhosDoces.domain.enums.TipoDoce;
import com.rj.doceria.sonhosDoces.domain.model.Doce;
import com.rj.doceria.sonhosDoces.service.DoceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DoceController.class)
public class DoceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DoceService doceService;

    @Test
     void getAllDoces() throws Exception {
        List<Doce> doceList = new ArrayList<>();
        doceList.add(new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20));
        doceList.add(new Doce(UUID.randomUUID(), "Pudim", TipoDoce.DOCE_DE_LEITE, 1, 5));
        Mockito.when(doceService.findAll()).thenReturn(doceList);

        mockMvc.perform(get("/doceria/doces").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2))).andDo(print());

    }

    @Test
    void getDoce() throws Exception {
        var doce = new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20);

        Mockito.when(doceService.findById(doce.getDoceId())).thenReturn(doce);

        mockMvc.perform(get("/doceria/doce/" + doce.getDoceId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(doce), false))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createDoce() throws Exception {
        var doce = new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20);

        Mockito.when(doceService.save(any(Doce.class))).thenReturn(doce);

        var mapper = new ObjectMapper();
        var jsonDoce = mapper.writeValueAsString(doce);

        var resultActions = mockMvc.perform(post("/doceria/doce").contentType(MediaType.APPLICATION_JSON).content(jsonDoce));

        resultActions.andExpect(status().isCreated())
                     .andExpect(content().json(jsonDoce))
                .andExpect(jsonPath("$.nome").value("Bolo"));
    }

    @Test
    public void editDoce()  throws Exception {
        var doce = (new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20));

        Mockito.when(doceService.save(any(Doce.class))).thenReturn(doce);

        var mapper = new ObjectMapper();
        var jsonDoce = mapper.writeValueAsString(doce);

        var resultActions = mockMvc.perform(post("/doceria/doce").contentType(MediaType.APPLICATION_JSON).content(jsonDoce));

        resultActions.andExpect(status().isCreated())
                .andExpect(content().json(jsonDoce))
                .andExpect(jsonPath("$.nome").value("Bolo"));
    }

    @Test
    void deleteDoce() throws Exception {
        var doce = new Doce(UUID.randomUUID(), "Bolo", TipoDoce.COMUM, 10, 20);
        var doceServiceMock = mock(DoceService.class);
        doNothing().when(doceServiceMock).deleteById(any(UUID.class));

        mockMvc.perform(delete("/doceria/doce/" + doce.getDoceId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}
