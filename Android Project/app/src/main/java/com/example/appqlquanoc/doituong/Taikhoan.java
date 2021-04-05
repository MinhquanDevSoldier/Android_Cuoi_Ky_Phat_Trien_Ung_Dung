package com.example.appqlquanoc.doituong;

public class Taikhoan {
    private int IDTK;
    private String TenTK,Matkhau;
    private String LoaiTk;
    private String tenNV;
    private String ngaySinh;
    private String CMND;
    private String chucVu;
    private int mucLuong;

    public Taikhoan() {
    }

    public Taikhoan(int IDTK, String tenTK, String matkhau, String loaiTk, String tenNV, String ngaySinh, String CMND, String chucVu, int mucLuong) {
        this.IDTK = IDTK;
        TenTK = tenTK;
        Matkhau = matkhau;
        LoaiTk = loaiTk;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.CMND = CMND;
        this.chucVu = chucVu;
        this.mucLuong = mucLuong;
    }

    public Taikhoan(int id, String tentk,String matkhau, String chucVu) {
        this.IDTK = id;
        this.TenTK = tentk;
        this.Matkhau = matkhau;
        this.chucVu = chucVu;
    }

    public int getIDTK() {
        return IDTK;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String matkhau) {
        Matkhau = matkhau;
    }

    public String getLoaiTk() {
        return LoaiTk;
    }

    public void setLoaiTk(String loaiTk) {
        LoaiTk = loaiTk;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(int mucLuong) {
        this.mucLuong = mucLuong;
    }
}
