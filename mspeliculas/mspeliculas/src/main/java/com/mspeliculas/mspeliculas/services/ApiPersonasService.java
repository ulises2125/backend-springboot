package com.mspeliculas.mspeliculas.services;

import com.mspeliculas.mspeliculas.model.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ApiPersonasService {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Persona> getPersonaById(Long id){
        String url = "http://localhost:8080/api/personas/id/{id}";
        Map<String,Long> urlVariable = new HashMap<>();
        urlVariable.put("id",id);
        try {
            ResponseEntity<Persona> response = restTemplate.getForEntity(url, Persona.class, urlVariable);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
