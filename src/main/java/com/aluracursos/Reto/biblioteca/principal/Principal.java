package com.aluracursos.Reto.biblioteca.principal;

import com.aluracursos.Reto.biblioteca.Model.Autor;
import com.aluracursos.Reto.biblioteca.Model.Datos;
import com.aluracursos.Reto.biblioteca.Model.DatosLibro;
import com.aluracursos.Reto.biblioteca.Model.Libro;
import com.aluracursos.Reto.biblioteca.Services.ConsumoAPI;
import com.aluracursos.Reto.biblioteca.Services.ConvierteDatos;
import com.aluracursos.Reto.biblioteca.repository.AutorRepository;
import com.aluracursos.Reto.biblioteca.repository.LibroRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books?search=Twenty+Thousand+Leagues";
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepository;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.libroRepositorio = repository;
        this.autorRepository = autorRepository;
    }

    private void leerLibro(Libro libro) {
        System.out.printf("""
                        ----- LIBRO -----
                        Titulo: %s
                        Autor: %s
                        Idioma: %s
                        Numero de descargas: %d
                        -------------------- \n
                        """,
                libro.getTitulo(),
                libro.getAutor().getNombre(),
                libro.getIdioma(),
                libro.getDescargas());
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarAutoresPorAño();
                    break;
                case 5:
                    mostrarLibrosPoridioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro getDatosLibro() {
//        System.out.println("Escribe el título del libro: ");
//        String nombreLibro = teclado.nextLine();
        System.out.println("url: "+URL_BASE);
        String json = consumoApi.obtenerDatos(URL_BASE);
        System.out.println("json: "+json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        if (datos.resultados() != null && !datos.resultados().isEmpty()) {
            return datos.resultados().get(0); // Tomar el primer resultado
        }
        return null;
    }

    private Libro almacenarLibro(DatosLibro datosLibro, Autor autor) {
        List<Libro> existingLibros = libroRepositorio.findByTitulo(datosLibro.titulo());
        if (!existingLibros.isEmpty()) {
            return existingLibros.get(0); // Devuelve el primer libro encontrado
        }
        Libro libro = new Libro(datosLibro, autor);
        return libroRepositorio.save(libro);
    }



    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            Autor autor = datos.autor().stream()
                    .map(da -> {
                        Autor existingAutor = autorRepository.findByNombre(da.nombre());
                        if (existingAutor != null) {
                            return existingAutor;
                        } else {
                            Autor nuevoAutor = new Autor(da);
                            return autorRepository.save(nuevoAutor);
                        }
                    })
                    .findFirst()
                    .orElse(null);
            if (autor != null) {
                Libro libro = almacenarLibro(datos, autor);
                leerLibro(libro);
            }
        } else {
            System.out.println("Sin resultados");

        }
    }
    private void mostrarLibros(){
        List<Libro> libros = libroRepositorio.findAll();
        libros.forEach(this::leerLibro);
    }
    private void mostrarAutores(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }
    private void mostrarAutoresPorAño(){
        System.out.println("Ingrese el año a partir se desea buscar: ");
        var año=teclado.nextInt();
        List<Autor> autores = autorRepository.buscarAutoresVivos(año);
        autores.forEach(System.out::println);
    }
    private void mostrarLibrosPoridioma(){
        System.out.println("Ingrese el idioma deseado de la forma \n");
        System.out.println("en - Ingles\n  es - Español\n");
        var idioma = teclado.nextLine();
        List<Libro> librosIdioma = libroRepositorio.findByIdioma(idioma);
        librosIdioma.forEach(this::leerLibro);
    }

}
