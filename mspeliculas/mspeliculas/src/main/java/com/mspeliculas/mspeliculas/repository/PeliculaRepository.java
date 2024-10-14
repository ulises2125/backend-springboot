package com.mspeliculas.mspeliculas.repository;

import com.mspeliculas.mspeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PeliculaRepository {
    @Value("${config.maxMovies}")
    private Integer maxMovies;

    private List<Pelicula> peliculas = new ArrayList<>();
    private Long nextId = 1L;

    public List<Pelicula> findAll() {
        return peliculas.stream()
                .sorted(Comparator.comparing(Pelicula::getTitle))
                .collect(Collectors.toList());
    }

    public List<Pelicula> findById(Long id) {
        return peliculas.stream().filter(p -> p.getPersonaId().equals(id)).collect(Collectors.toList());

    }

    public void save(Pelicula pelicula) {
        if (pelicula.getId() == null) {
            pelicula.setId(nextId++);
        }
        AtomicInteger contador = new AtomicInteger(0);  // Usar AtomicInteger para el contador
        peliculas.forEach(p -> {
            if (p.getPersonaId().equals(pelicula.getPersonaId())) {
                contador.incrementAndGet();  // Incrementar el contador
            }
        });
        if (contador.get() < maxMovies){
            peliculas.add(pelicula);
        }
        else {
            throw new RuntimeException("La persona ya tiene el máximo permitido de películas.");
        }
    }

    public void delete(Long personaId, Long id) {
        peliculas.removeIf(p -> p.getPersonaId().equals(personaId) && p.getId().equals(id) );
    }
}
