package com.example.appqlquanoc.doituong;

public class KhuVuc {
    private int idkv;
    private String tenKhuVuc;

    public KhuVuc() {
        super();
    }

    public KhuVuc(int idkv, String tenKhuVuc) {
        super();
        this.idkv = idkv;
        this.tenKhuVuc = tenKhuVuc;
    }

    public int getIdkv() {
        return idkv;
    }

    public void setIdkv(int idkv) {
        this.idkv = idkv;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }
}