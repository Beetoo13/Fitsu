package com.example.fitsu;

public class Publicacion {
    private int outfitPost;
    private String nameP, contentP;

    public Publicacion(){

    }

    public Publicacion(int outfitPost, String nameP, String contentP) {
        this.outfitPost = outfitPost;
        this.nameP = nameP;
        this.contentP = contentP;
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
