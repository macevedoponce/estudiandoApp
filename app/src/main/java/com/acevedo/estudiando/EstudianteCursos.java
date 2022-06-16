package com.acevedo.estudiando;

public class EstudianteCursos {

    String id,codigo,nombre,docente,grado,seccion;

    public EstudianteCursos(){

    }

    public EstudianteCursos(String id, String codigo, String nombre, String docente, String grado, String seccion) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.docente = docente;
        this.grado = grado;
        this.seccion = seccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}

