package com.mspersonas.mspersonas.services;

import com.mspersonas.mspersonas.model.Pelicula;
import com.mspersonas.mspersonas.model.Persona;
import com.mspersonas.mspersonas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository repository;

    @Autowired
    private ApiPeliculasServices apiPeliculasServices;

    public List<Persona> getAllPersonas() {
        List<Persona> personas = repository.findAll();
        if(!personas.isEmpty()) {
            List<Persona> personaList = new ArrayList<>();
            personas.forEach(persona -> {
                ResponseEntity<List<Pelicula>> response = apiPeliculasServices.getPeliculaById(persona.getId());
                persona.setFavouriteMovies(response.getBody());
                personaList.add(persona);
            });
            return personaList;
        }
        return null;
    }

    public Persona getPersonaById(Long id) {
        Optional<Persona> persona = repository.findById(id);
        if (persona.isPresent()) {
            ResponseEntity<List<Pelicula>> response = apiPeliculasServices.getPeliculaById(id);
            persona.get().setFavouriteMovies(response.getBody());
            return persona.get();
        }
        return null;
    }

    public List<Persona> getPersonaByName(String firstName){
        List<Persona> persona = repository.findByName(firstName);
        if (!persona.isEmpty()) {
            return persona;
        }
        return null;
    }

    public void createPersona(Persona persona){
        if (repository.existsById(persona.getId())) {
            throw new IllegalArgumentException("Ya existe una persona con el mismo ID: " + persona.getId());
        }
        repository.save(persona);
    }

    public void deletePersona(Long id){
        repository.delete(id);
    }

    public void modifyPersona(Long id, Persona newPersona){
        Optional<Persona> persona = repository.findById(id);
        if (persona.isPresent()) {
            if(!newPersona.getFirstName().isEmpty()) persona.get().setFirstName(newPersona.getFirstName());
            if(!newPersona.getLastName().isEmpty()) persona.get().setLastName(newPersona.getLastName());
            if(newPersona.getBirthdate() != null) persona.get().setBirthdate(newPersona.getBirthdate());
            persona.get().setHasInsurance(newPersona.getHasInsurance());
            //if(newPersona.getFavouriteMovies() != null) persona.get().setFavouriteMovies(newPersona.getFavouriteMovies());
        }
    }
}
