package com.mspersonas.mspersonas.services;

import com.mspersonas.mspersonas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiPeliculasServices {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List<Pelicula>> getPeliculaById(Long id){
        String url = "http://localhost:8081/api/peliculas/{id}";
        Map<String,Long> urlVariable = new HashMap<>();
        urlVariable.put("id",id);
        ResponseEntity<List<Pelicula>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Pelicula>>() {},
                urlVariable
        );
        return response;
    }
}
