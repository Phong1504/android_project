package com.example.mucsicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TheLoai implements Serializable {

    @SerializedName("IDTheLoai")
    @Expose
    private String iDTheLoai;
    @SerializedName("Idkeychude")
    @Expose
    private String idkeychude;
    @SerializedName("TenTheLoai")
    @Expose
    private String tenTheLoai;
    @SerializedName("HinhTheLoai")
    @Expose
    private String hinhTheLoai;

    public String getIDTheLoai() {
        return iDTheLoai;
    }

    public void setIDTheLoai(String iDTheLoai) {
        this.iDTheLoai = iDTheLoai;
    }

    public String getIdkeychude() {
        return idkeychude;
    }

    public void setIdkeychude(String idkeychude) {
        this.idkeychude = idkeychude;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getHinhTheLoai() {
        return hinhTheLoai;
    }

    public void setHinhTheLoai(String hinhTheLoai) {
        this.hinhTheLoai = hinhTheLoai;
    }
}

