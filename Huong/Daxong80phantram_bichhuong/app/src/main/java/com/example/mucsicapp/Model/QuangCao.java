package com.example.mucsicapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class QuangCao implements Serializable {

    @SerializedName("IdQuangcao")
    @Expose
    private String idQuangcao;
    @SerializedName("HinhanhQuangcao")
    @Expose
    private String hinhanhQuangcao;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("IdBaihat")
    @Expose
    private String idBaihat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaihat")
    @Expose
    private String hinhBaihat;

    public String getIdQuangcao() {
        return idQuangcao;
    }

    public void setIdQuangcao(String idQuangcao) {
        this.idQuangcao = idQuangcao;
    }

    public String getHinhanhQuangcao() {
        return hinhanhQuangcao;
    }

    public void setHinhanhQuangcao(String hinhanhQuangcao) {
        this.hinhanhQuangcao = hinhanhQuangcao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getIdBaihat() {
        return idBaihat;
    }

    public void setIdBaihat(String idBaihat) {
        this.idBaihat = idBaihat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaihat() {
        return hinhBaihat;
    }

    public void setHinhBaihat(String hinhBaihat) {
        this.hinhBaihat = hinhBaihat;
    }

}