package com.acevedo.estudiando;

public class Horarios {

    String id,cursoCodigo,cursoNombre,horario;

    public Horarios(){

    }

    public Horarios(String id, String cursoCodigo, String cursoNombre, String horario) {
        this.id = id;
        this.cursoCodigo = cursoCodigo;
        this.cursoNombre = cursoNombre;
        this.horario = horario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCursoCodigo() {
        return cursoCodigo;
    }

    public void setCursoCodigo(String cursoCodigo) {
        this.cursoCodigo = cursoCodigo;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}

