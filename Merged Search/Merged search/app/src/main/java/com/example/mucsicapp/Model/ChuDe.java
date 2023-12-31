package com.example.mucsicapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ChuDe implements Serializable {

        @SerializedName("IDchude")
        @Expose
        private String iDchude;
        @SerializedName("TenChude")
        @Expose
        private String tenChude;
        @SerializedName("HinhChude")
        @Expose
        private String hinhChude;

        public String getIDchude() {
            return iDchude;
        }

        public void setIDchude(String iDchude) {
            this.iDchude = iDchude;
        }

        public String getTenChude() {
            return tenChude;
        }

        public void setTenChude(String tenChude) {
            this.tenChude = tenChude;
        }

        public String getHinhChude() {
            return hinhChude;
        }

        public void setHinhChude(String hinhChude) {
            this.hinhChude = hinhChude;
        }

    }

