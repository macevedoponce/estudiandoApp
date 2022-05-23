package com.acevedo.estudiando;

public class Tareas {
    String id,titulo,descripcion,retroalimentacion;

    public Tareas(){

    }

    public Tareas(String id, String titulo, String descripcion, String retroalimentacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.retroalimentacion = retroalimentacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }
}
