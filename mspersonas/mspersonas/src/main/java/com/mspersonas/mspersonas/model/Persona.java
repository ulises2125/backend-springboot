package com.mspersonas.mspersonas.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Persona {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Boolean hasInsurance;
    private List<Pelicula> favouriteMovies;
}
