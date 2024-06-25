package com.aluracursos.Reto.biblioteca.repository;

import com.aluracursos.Reto.biblioteca.Model.Autor;
import com.aluracursos.Reto.biblioteca.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {


    @Query("SELECT a FROM Autor a WHERE a.añoMuerte > :fecha OR a.añoMuerte IS NULL")
    List<Autor> buscarAutoresVivos(@Param("fecha") Integer fecha);

    List<Autor> findAll();

    List<Autor> findByAñoNacimientoLessThanEqualAndAñoMuerteGreaterThanEqual(int añoNacimiento, int añoMuerte);
    List<Autor> findByAñoMuerteIsNullOrAñoMuerteGreaterThanEqual(int añoMuerte);

    Autor findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Autor> buscarAutorPorNombre(@Param("nombre") String nombre);


}

