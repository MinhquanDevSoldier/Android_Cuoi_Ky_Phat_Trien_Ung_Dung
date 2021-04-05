package com.example.appqlquanoc.doituong;

public class CTHD {
    private int idctthd;
    private int idhd;
    private int idsanpham;
    private int soluong;

    public CTHD() {
    }

    public CTHD(int idctthd, int ihhd, int idsanpham, int soluong) {
        this.idctthd = idctthd;
        this.idhd = ihhd;
        this.idsanpham = idsanpham;
        this.soluong = soluong;
    }

    public int getIdctthd() {
        return idctthd;
    }

    public void setIdctthd(int idctthd) {
        this.idctthd = idctthd;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
