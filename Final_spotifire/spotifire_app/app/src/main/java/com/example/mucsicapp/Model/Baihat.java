package com.example.mucsicapp.Model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Baihat implements Parcelable {

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

    protected Baihat(Parcel in) {
        idbaiHat = in.readString();
        tenBaihat = in.readString();
        hinhBaihat = in.readString();
        casi = in.readString();
        linkBaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idbaiHat);
        dest.writeString(tenBaihat);
        dest.writeString(hinhBaihat);
        dest.writeString(casi);
        dest.writeString(linkBaihat);
        dest.writeString(luotthich);
    }
}
