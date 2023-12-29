package com.example.mucsicapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("idalbum")
    @Expose
    private String idalbum;
    @SerializedName("TenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("tencasiAlbum")
    @Expose
    private String tencasiAlbum;
    @SerializedName("HinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdalbum() {
        return idalbum;
    }

    public void setIdalbum(String idalbum) {
        this.idalbum = idalbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTencasiAlbum() {
        return tencasiAlbum;
    }

    public void setTencasiAlbum(String tencasiAlbum) {
        this.tencasiAlbum = tencasiAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }

}
