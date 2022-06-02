package com.acevedo.estudiando;

public class Alumnos {
    String id,alumno;

    public Alumnos(){

    }

    public Alumnos(String id, String alumno) {
        this.id = id;
        this.alumno = alumno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
}
