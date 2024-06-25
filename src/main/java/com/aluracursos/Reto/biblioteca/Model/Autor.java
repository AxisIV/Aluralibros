package com.aluracursos.Reto.biblioteca.Model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer añoNacimiento;
    private Integer añoMuerte;

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro>libros;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.añoNacimiento = datosAutor.añoNacimiento();
        this.añoMuerte = datosAutor.añoMuerte();
    }
    public Autor(String nombre, Integer añoNacimiento, Integer añoMuerte) {
        this.nombre = nombre;
        this.añoNacimiento = añoNacimiento;
        this.añoMuerte = añoMuerte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(Integer añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public Integer getAñoMuerte() {
        return añoMuerte;
    }

    public void setAñoMuerte(Integer añoMuerte) {
        this.añoMuerte = añoMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", añoNacimiento=" + añoNacimiento +
                ", añoMuerte=" + añoMuerte +
                '}';
    }
}
