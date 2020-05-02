package com.example.fitsu;

public class Historial {
    private int outfitEnfoque;
    private int outfitPequeño;
    private String fecha;

    public Historial(){

    }

    public Historial(int outfitEnfoque, int outfitPequeño, String fecha) {
        this.outfitEnfoque = outfitEnfoque;
        this.outfitPequeño = outfitPequeño;
        this.fecha = fecha;
    }

    public int getOutfitEnfoque() {
        return outfitEnfoque;
    }

    public void setOutfitEnfoque(int outfitEnfoque) {
        this.outfitEnfoque = outfitEnfoque;
    }

    public int getOutfitPequeño() {
        return outfitPequeño;
    }

    public void setOutfitPequeño(int outfitPequeño) {
        this.outfitPequeño = outfitPequeño;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
