package com.mspeliculas.mspeliculas.model;

import lombok.Data;

@Data
public class Pelicula {
    private Long id;
    private Long personaId;
    private String title;
    private String genre;

    }

