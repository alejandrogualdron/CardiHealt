package com.example.cardihealt.Informacion;

public class List_Informacion {
    private String informacion;
    private String descripcion;
    private Integer movieImage;

    public List_Informacion(String informacion, String descripcion, Integer movieImage) {
        this.informacion = informacion;
        this.descripcion = descripcion;
        this.movieImage = movieImage;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(Integer movieImage) {
        this.movieImage = movieImage;
    }

}
