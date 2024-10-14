package com.mspersonas.mspersonas.controller;

import com.mspersonas.mspersonas.model.Persona;
import com.mspersonas.mspersonas.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/personas")
@Controller
public class PersonaController {
    @Autowired
    public PersonaService service;

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersona() {
        List<Persona> personas = service.getAllPersonas();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Persona>> getPersonaByName(@PathVariable String firstName){
        List<Persona> personas = service.getPersonaByName(firstName);
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id){
        Persona personas = service.getPersonaById(id);
        return ResponseEntity.ok(personas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id){
        service.deletePersona(id);
        return ResponseEntity.ok("Se borró exitosamente");
    }

    @PostMapping
    public ResponseEntity<String> createPersona(@RequestBody Persona persona) {
        try {
            service.createPersona(persona);
            return ResponseEntity.ok("Se creó exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyPersona(@PathVariable Long id, @RequestBody Persona persona){
        service.modifyPersona(id, persona);
        return ResponseEntity.ok("Se modificó exitosamente");
    }
}
