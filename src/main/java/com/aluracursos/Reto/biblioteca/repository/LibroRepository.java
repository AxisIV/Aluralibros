package com.aluracursos.Reto.biblioteca.repository;

import com.aluracursos.Reto.biblioteca.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByIdioma(String idioma);
    List<Libro> findByTitulo(String titulo);
    List<Libro> findAll();

}
