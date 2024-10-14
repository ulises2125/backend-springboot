package com.mspeliculas.mspeliculas.controller;

import com.mspeliculas.mspeliculas.model.Pelicula;
import com.mspeliculas.mspeliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService service;

    @GetMapping
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = service.getAllPeliculas();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Pelicula>> getPeliculaById(@PathVariable Long id) {
        List<Pelicula> pelicula = service.getPeliculaById(id);
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<String> createPelicula(@RequestBody Pelicula pelicula) {
        try {
            service.createPelicula(pelicula);
            return ResponseEntity.ok("Película creada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        }


    @DeleteMapping("/{personaId}/{id}")
    public ResponseEntity<String> deletePelicula(@PathVariable Long personaId,@PathVariable Long id) {
        service.deletePelicula(personaId, id);
        return ResponseEntity.ok("Película eliminada exitosamente");
    }
}
