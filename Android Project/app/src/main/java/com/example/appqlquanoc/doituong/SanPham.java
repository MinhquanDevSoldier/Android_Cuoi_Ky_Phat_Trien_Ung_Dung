package com.example.appqlquanoc.doituong;

public class SanPham {
    private int idMon;
    private String tenMon;
    private int maDM;
    private int giaMon;

    public SanPham() {
        super();
    }

    public SanPham(int idMon, int maDM,String tenMon, int giaMon) {
        super();
        this.idMon = idMon;
        this.tenMon = tenMon;
        this.maDM = maDM;
        this.giaMon = giaMon;

    }


    public int getIdMon() {
        return idMon;
    }

    public void setIdMon(int idMon) {
        this.idMon = idMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public int getGiaMon() {
        return giaMon;
    }

    public void setGiaMon(int giaMon) {
        this.giaMon = giaMon;
    }
}
