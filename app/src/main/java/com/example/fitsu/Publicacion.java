package com.example.fitsu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Publicacion {
    private int outfitPost;
    private String nameP, contentP;

    private String outfitStr;
    private Bitmap outfitBit;

    public Publicacion(){

    }

    public Publicacion(int outfitPost, String nameP, String contentP) {
        this.outfitPost = outfitPost;
        this.nameP = nameP;
        this.contentP = contentP;
    }

    public String getOutfitStr() {
        return outfitStr;
    }

    public void setOutfitStr(String outfitStr) {
        this.outfitStr = outfitStr;

        try {
            byte[] byteCode= Base64.decode(outfitStr, Base64.DEFAULT);//Decodifica el string "dato"
            this.outfitBit = BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getOutfitBit() {
        return outfitBit;
    }

    public void setOutfitBit(Bitmap outfitBit) {
        this.outfitBit = outfitBit;
    }

    public int getOutfitPost() {
        return outfitPost;
    }

    public void setOutfitPost(int outfitPost) {
        this.outfitPost = outfitPost;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getContentP() {
        return contentP;
    }

    public void setContentP(String contentP) {
        this.contentP = contentP;
    }

}
