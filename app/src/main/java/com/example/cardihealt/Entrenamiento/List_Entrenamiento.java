package com.example.cardihealt.Entrenamiento;

public class List_Entrenamiento {
    private String ejercicio;

    private Integer imagenEntrenamiento;

    public List_Entrenamiento(String ejercicio, Integer imagenEntrenamiento) {
        this.ejercicio = ejercicio;
        this.imagenEntrenamiento = imagenEntrenamiento;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }


    public Integer getImagenEntrenamiento() {
        return imagenEntrenamiento;
    }

    public void setImagenEntrenamiento(Integer imagenEntrenamiento) {
        this.imagenEntrenamiento = imagenEntrenamiento;
    }


}
