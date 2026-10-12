package com.brescolanovaeratech.SpringCep.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.brescolanovaeratech.SpringCep.domain.CepDomain;

@WebMvcTest(ConsultaController.class)
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestTemplate restTemplate;

    @Test
    void cepValidoRetornaEndereco() throws Exception {
        CepDomain sp = new CepDomain();
        sp.setCep("01001-000");
        sp.setLogradouro("Praça da Sé");
        sp.setLocalidade("São Paulo");
        sp.setUf("SP");
        when(restTemplate.getForEntity("https://viacep.com.br/ws/01001000/json", CepDomain.class))
                .thenReturn(ResponseEntity.ok(sp));

        mockMvc.perform(get("/consulta/01001000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("01001-000"))
                .andExpect(jsonPath("$.localidade").value("São Paulo"))
                .andExpect(jsonPath("$.uf").value("SP"));
    }

    @Test
    void cepInexistenteRetorna404() throws Exception {
        when(restTemplate.getForEntity(anyString(), eq(CepDomain.class)))
                .thenReturn(ResponseEntity.ok(new CepDomain()));

        mockMvc.perform(get("/consulta/99999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void cepCurtoRetorna400SemChamarViaCep() throws Exception {
        mockMvc.perform(get("/consulta/123"))
                .andExpect(status().isBadRequest());

        verify(restTemplate, never()).getForEntity(anyString(), eq(CepDomain.class));
    }

    @Test
    void cepComLetrasRetorna400() throws Exception {
        mockMvc.perform(get("/consulta/abcdefgh"))
                .andExpect(status().isBadRequest());
    }
}
