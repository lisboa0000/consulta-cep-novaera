package com.brescolanovaeratech.SpringCep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.brescolanovaeratech.SpringCep.domain.CepDomain;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/%s/json";

    private final RestTemplate restTemplate;

    public ConsultaController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{cep}")
    public CepDomain consultaCep(@PathVariable("cep") String cep) {
        if (!cep.matches("[0-9]{8}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido: informe 8 dígitos numéricos");
        }

        ResponseEntity<CepDomain> resp = restTemplate.getForEntity(
                String.format(VIACEP_URL, cep),
                CepDomain.class
        );

        CepDomain body = resp.getBody();
        // O ViaCEP responde {"erro": true} para CEPs inexistentes, o que deixa todos os campos nulos
        if (body == null || body.getCep() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado");
        }
        return body;
    }

}
