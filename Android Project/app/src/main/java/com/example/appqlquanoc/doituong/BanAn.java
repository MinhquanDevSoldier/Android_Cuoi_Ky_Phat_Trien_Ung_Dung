package com.example.appqlquanoc.doituong;

import java.io.Serializable;

public class BanAn implements Serializable {
    int idBan;
    int idkv;
    String tenBan;
    boolean trangthai;

    public BanAn() {
        super();
    }

    public BanAn(int idBan,int idkv,String tenBan, boolean trangthai) {
        super();
        this.idBan = idBan;
        this.idkv = idkv;
        this.tenBan = tenBan;
        this.trangthai = trangthai;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public int getIdkv(){ return idkv;}

    public void setIdkv(int idkv){this.idkv = idkv;}

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
}
