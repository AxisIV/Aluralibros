package com.aluracursos.Reto.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(@JsonAlias("name") String nombre,
                         @JsonAlias("birth_year") Integer añoNacimiento,
                         @JsonAlias("death_year") Integer añoMuerte
                         ) {
}
