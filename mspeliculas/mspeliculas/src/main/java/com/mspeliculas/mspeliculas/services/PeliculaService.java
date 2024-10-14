package com.mspeliculas.mspeliculas.service;

import com.mspeliculas.mspeliculas.model.Pelicula;
import com.mspeliculas.mspeliculas.model.Persona;
import com.mspeliculas.mspeliculas.repository.PeliculaRepository;
import com.mspeliculas.mspeliculas.services.ApiPersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository repository;

    @Autowired
    private ApiPersonasService apiPersonasService;

    public List<Pelicula> getAllPeliculas() {
        return repository.findAll();
    }

    public List<Pelicula> getPeliculaById(Long id) {
        return repository.findById(id);
    }

    public void createPelicula(Pelicula pelicula) {
        ResponseEntity<Persona> response = apiPersonasService.getPersonaById(pelicula.getPersonaId());
        if (response != null && response.getBody() != null) {
            repository.save(pelicula);
        } else {
            throw new RuntimeException("No se puede crear la película. La persona con ID " + pelicula.getPersonaId() + " no existe.");
        }
    }

    public void deletePelicula(Long personaId,Long id) {
        repository.delete(personaId, id);
    }
}
