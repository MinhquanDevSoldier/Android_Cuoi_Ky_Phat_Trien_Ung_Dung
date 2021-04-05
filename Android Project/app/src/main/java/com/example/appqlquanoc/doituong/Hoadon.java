package com.example.appqlquanoc.doituong;

import java.sql.Date;

public class Hoadon {
    private int idhd;
    private String idtk;
    private int idban;
    private String tgvao;
    private String tgra;
    private float tongTien;
    private boolean trangThai;

    public Hoadon() {
    }

    public Hoadon(int idhd, String idtk, int idban,String tgvao, String tgra, float tongTien, boolean trangThai) {
        this.idhd = idhd;
        this.idtk = idtk;
        this.idban = idban;
        this.tgvao = tgvao;
        this.tgra = tgra;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public String getIdtk() {
        return idtk;
    }

    public void setIdtk(String idtk) {
        this.idtk = idtk;
    }

    public int getIdban() {
        return idban;
    }

    public void setIdban(int idban) {
        this.idban = idban;
    }

    public String getTgvao() {
        return tgvao;
    }

    public void setTgvao(String tgvao) {
        this.tgvao = tgvao;
    }

    public String getTgra() {
        return tgra;
    }

    public void setTgra(String tgra) {
        this.tgra = tgra;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
