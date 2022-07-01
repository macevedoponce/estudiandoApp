package com.acevedo.estudiando;

public class ExamenEstudianteRetro {

    String id,pregunta,alternativa1,retroalimentacion;

    public ExamenEstudianteRetro() {
    }

    public ExamenEstudianteRetro(String id, String pregunta, String alternativa1, String retroalimentacion) {
        this.id = id;
        this.pregunta = pregunta;
        this.alternativa1 = alternativa1;
        this.retroalimentacion = retroalimentacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }
}
