package com.example.fitsu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Historial {
    private int outfitEnfoque;
    private int outfitPequeño;
    private String fecha;

    //variables para traer la imagen de la bdd
    private String datoG; // Aqui se guarda el string de la bdd
    private String datoP;
    private Bitmap outfitG;
    private Bitmap outfitP;

    public Historial(){

    }

    public Historial(int outfitEnfoque, int outfitPequeño, String fecha) {
        this.outfitEnfoque = outfitEnfoque;
        this.outfitPequeño = outfitPequeño;
        this.fecha = fecha;
    }

    //get & set de imagen bdd------------------------------

    public String getDatoG() {
        return datoG;
    }

    public void setDatoG(String datoG) {
        this.datoG = datoG;

        try {
            byte[] byteCode= Base64.decode(datoG, Base64.DEFAULT);//Decodifica el string "dato"
            this.outfitG = BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Bitmap getOutfitG() {
        return outfitG;
    }

    public void setOutfitG(Bitmap outfitG) {
        this.outfitG = outfitG;
    }

    public String getDatoP() {
        return datoP;
    }

    public void setDatoP(String datoP) {
        this.datoP = datoP;

        try {
            byte[] byteCode= Base64.decode(datoP, Base64.DEFAULT);//Decodifica el string "dato"
            this.outfitP = BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getOutfitP() {
        return outfitP;
    }

    public void setOutfitP(Bitmap outfitP) {
        this.outfitP = outfitP;
    }

    //-----------------------------------------------------

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
