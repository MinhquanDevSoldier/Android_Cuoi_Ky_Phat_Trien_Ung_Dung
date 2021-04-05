package com.example.appqlquanoc.doituong;

import java.util.Date;

public class Nhanvien {
    private String idnv,tenNV;
    private int idtk;
    private Date ngaySinh;
    private String cmnd,sdt;
    private String mucluong;

    public Nhanvien() {
        super();
    }

    public Nhanvien(String idnv, String tenNV, Date ngaySinh, String cmnd, String sdt, String mucluong) {
        super();
        this.idnv = idnv;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.mucluong = mucluong;
    }

    public String getIdnv() {
        return idnv;
    }

    public void setIdnv(String idnv) {
        this.idnv = idnv;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMucluong() {
        return mucluong;
    }

    public void setMucluong(String mucluong) {
        this.mucluong = mucluong;
    }
}
