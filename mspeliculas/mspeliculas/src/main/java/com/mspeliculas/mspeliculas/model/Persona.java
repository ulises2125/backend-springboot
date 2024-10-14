package com.mspeliculas.mspeliculas.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Persona {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Boolean hasInsurance;
    private List<PeliculaResponse> favouriteMovies;

}