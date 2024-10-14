package com.mspersonas.mspersonas.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mspersonas.mspersonas.model.Persona;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository {
    private List<Persona> persons = new ArrayList<>();

    public List<Persona> findAll() {
        return persons.stream()
                .sorted(Comparator.comparing(Persona::getLastName).thenComparing(Persona::getFirstName))
                .collect(Collectors.toList());
    }

    public Optional<Persona> findById(Long id) {
        return persons.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Persona> findByName(String name) {
        return persons.stream().filter(p -> p.getFirstName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public void save(Persona person) {
        persons.add(person);
    }

    public void delete(Long id) {
        persons.removeIf(p -> p.getId().equals(id));
    }

    public boolean existsById(Long id) {
        return persons.stream().anyMatch(p -> p.getId().equals(id));
    }
}
