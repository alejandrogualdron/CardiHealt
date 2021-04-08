package com.example.cardihealt.Nutricion;

public class List_Nutricion {


    private String alimento;
    private String leyendaAlimento;
    private Integer imagenAlimento;

    public List_Nutricion(String alimento, String leyendaAlimento, Integer imagenAlimento) {
        this.alimento = alimento;
        this.leyendaAlimento = leyendaAlimento;
        this.imagenAlimento = imagenAlimento;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getLeyendaAlimento() {
        return leyendaAlimento;
    }

    public void setLeyendaAlimento(String leyendaAlimento) {
        this.leyendaAlimento = leyendaAlimento;
    }

    public Integer getImagenAlimento() {
        return imagenAlimento;
    }

    public void setImagenAlimento(Integer imagenAlimento) {
        this.imagenAlimento = imagenAlimento;
    }
}
