package com.example.mucsicapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat {

    @SerializedName("IdbaiHat")
    @Expose
    private String idbaiHat;
    @SerializedName("TenBaihat")
    @Expose
    private String tenBaihat;
    @SerializedName("HinhBaihat")
    @Expose
    private String hinhBaihat;
    @SerializedName("Casi")
    @Expose
    private String casi;
    @SerializedName("LinkBaihat")
    @Expose
    private String linkBaihat;
    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    public String getIdbaiHat() {
        return idbaiHat;
    }

    public void setIdbaiHat(String idbaiHat) {
        this.idbaiHat = idbaiHat;
    }

    public String getTenBaihat() {
        return tenBaihat;
    }

    public void setTenBaihat(String tenBaihat) {
        this.tenBaihat = tenBaihat;
    }

    public String getHinhBaihat() {
        return hinhBaihat;
    }

    public void setHinhBaihat(String hinhBaihat) {
        this.hinhBaihat = hinhBaihat;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public String getLinkBaihat() {
        return linkBaihat;
    }

    public void setLinkBaihat(String linkBaihat) {
        this.linkBaihat = linkBaihat;
    }

    public String getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(String luotthich) {
        this.luotthich = luotthich;
    }

}
