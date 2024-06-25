package com.aluracursos.Reto.biblioteca.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "autor_libro")
public class AutorLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public AutorLibro() {}

    public AutorLibro(Libro libro, Autor autor) {
        this.libro = libro;
        this.autor = autor;
    }


    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
