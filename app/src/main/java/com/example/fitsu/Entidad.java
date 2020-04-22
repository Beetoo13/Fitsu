package com.example.fitsu;

public class Entidad {

    private int img_historial;
    private String fecha;

    public Entidad(int img_historial, String fecha) {
        this.img_historial = img_historial;
        this.fecha = fecha;
    }

    public int getImg_historial() {
        return img_historial;
    }

    public String getFecha() {
        return fecha;
    }
}
