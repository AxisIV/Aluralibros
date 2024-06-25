package com.aluracursos.Reto.biblioteca.Services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
