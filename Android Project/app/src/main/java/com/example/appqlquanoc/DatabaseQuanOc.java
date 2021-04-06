package com.example.appqlquanoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.CTHD;
import com.example.appqlquanoc.doituong.Hoadon;
import com.example.appqlquanoc.doituong.KhuVuc;
import com.example.appqlquanoc.doituong.SanPham;
import com.example.appqlquanoc.doituong.Taikhoan;

import java.sql.Date;
import java.util.ArrayList;

public class DatabaseQuanOc extends SQLiteOpenHelper {
    //Tao cac contraints
    //TAIKHOAN
    public static final String TAIKHOAN_TABLE = "TAIKHOAN_TABLE";
    public static final String COLUMN_IDTK_PK= "IDTK_PK";
    public static final String COLUMN_TENTK = "TENTK";
    public static final String COLUMN_MATKHAU = "MATKHAU";
    public static final String COLUMN_LOAITK = "LOAITK";
    public static final String COLUMN_TENNV = "TENNV";
    public static final String COLUMN_CMND = "CMND";
    public static final String COLUMN_NGAYSINH = "NGAYSINH";
    public static final String COLUMN_CHUCVU = "CHUCVU";
    public static final String COLUMN_MUCLUONG = "MUCLUONG";
    //BANAN
    public static final String BANAN_TABLE = "BANAN_TABLE";
    public static final String COLUMN_IDBAN_PK = "IDBAN_PK";
    public static final String COLUMN_IDKV_FK = "IDKV_FK";
    public static final String COLUMN_TENBAN = "TENBAN";
    public static final String COLUMN_TRANGTHAIBAN = "TRANGTHAIBAN";
    //KHUVUC
    public static final String KHUVUC_TABLE = "KHUVUC_TABLE";
    public static final String COLUMN_IDKV_PK = "IDKV_PK";
    public static final String COLUMN_TENKV = "TENKV";
    //SANPHAM
    public static final String SANPHAM_TABLE = "MONAN_TABLE";
    public static final String COLUMN_IDSP_PK = "IDSP_PK";
    public static final String COLUMN_IDDM = "IDDM";
    public static final String COLUMN_TENSP = "TENSP";
    public static final String COLUMN_GIASP = "GIASP";
    //HOADON
    public static final String HOADON_TABLE = "HOADON_TABLE";
    public static final String COLUMN_IDTK_FK = "IDTK_PK";
    public static final String COLUMN_IDHD_PK = "IDHD_PK";
    public static final String COLUMN_IDBAN_FK = "IDBAN_FK";
    public static final String COLUMN_TGVAO = "TGVAO";
    public static final String COLUMN_TGRA = "TGRA";
    public static final String COLUMN_TIENTHANHTOAN = "TIENTHANHTOAN";
    public static final String COLUMN_TRANGTHAITHANHTOAN = "TRANGTHAITHANHTOAN";
    //CTHD
    public static final String CTHOADON_TABLE = "CTHOADON_TABLE";
    public static final String COLUMN_IDCTHD = "IDCTHD";
    public static final String COLUMN_IDHD_FK = "IDHD_FK";
    public static final String COLUMN_IDSP_FK = "IDSP_FK";
    public static final String COLUMN_SOLUONG = "SOLUONG";



    public DatabaseQuanOc(@Nullable Context context) {

        super(context, "DatabaseQuanOc.db", null, 2);
    }
    //Truy vấn không trả kết quả : CREATE,INSERT,UPDATE,DELETE,...


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaiKhoanTable = "CREATE TABLE IF NOT EXISTS "+TAIKHOAN_TABLE+
                "("+COLUMN_IDTK_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_TENTK+" TEXT,"
                +COLUMN_MATKHAU+" TEXT,"
                +COLUMN_LOAITK+" INTEGER,"
                +COLUMN_TENNV+" TEXT,"
                +COLUMN_NGAYSINH+" TEXT,"
                +COLUMN_CMND+" TEXT,"
                +COLUMN_CHUCVU+" TEXT,"
                +COLUMN_MUCLUONG+" TEXT"
                +")";
        String createKhuVucTable = "CREATE TABLE IF NOT EXISTS "+KHUVUC_TABLE
                +"("+COLUMN_IDKV_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_TENKV+" TEXT"
                 +")";
        String createBanAnTable = "CREATE TABLE IF NOT EXISTS "+BANAN_TABLE+
                "("+COLUMN_IDBAN_PK +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_IDKV_FK+" INTEGER,"
                +COLUMN_TENBAN+" TEXT,"
                +COLUMN_TRANGTHAIBAN+" INTEGER,"
                +"FOREIGN KEY ("+COLUMN_IDKV_FK +") REFERENCES "+KHUVUC_TABLE+"("+COLUMN_IDKV_PK+")"
                +")";
        String createSanPhamTable = "CREATE TABLE IF NOT EXISTS "+SANPHAM_TABLE
                +"("+COLUMN_IDSP_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_IDDM+" INTEGER,"
                +COLUMN_TENSP+" TEXT,"
                +COLUMN_GIASP+" INTEGER"
                +")";
        String createHoadonTable = "CREATE TABLE IF NOT EXISTS "+HOADON_TABLE
                +"("+COLUMN_IDHD_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_IDTK_FK+" INTEGER,"
                +COLUMN_IDBAN_FK+" INTEGER,"
                +COLUMN_TGVAO+" TEXT,"
                +COLUMN_TGRA+" TEXT,"
                +COLUMN_TIENTHANHTOAN+" INTEGER,"
                +COLUMN_TRANGTHAITHANHTOAN+" INTEGER"
                +")";
        String createCTHDTable = "CREATE TABLE IF NOT EXISTS "+CTHOADON_TABLE
                +"("+COLUMN_IDCTHD+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_IDHD_FK+" INTEGER,"
                +COLUMN_IDSP_FK+" INTEGER,"
                +COLUMN_SOLUONG+" INTEGER"
                +")";
        db.execSQL(createTaiKhoanTable);
        db.execSQL(createKhuVucTable);
        db.execSQL(createBanAnTable);
        db.execSQL(createSanPhamTable);
        db.execSQL(createHoadonTable);
        db.execSQL(createCTHDTable);
        //Them du lieu
     }

    public void xoadatabase()
    {
//        String createKhuVucTable = "CREATE TABLE IF NOT EXISTS "+KHUVUC_TABLE
//                +"("+COLUMN_IDKV_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +COLUMN_TENKV+" TEXT"
//                +")";
//        String createBanAnTable = "CREATE TABLE IF NOT EXISTS "+BANAN_TABLE+
//                "("+COLUMN_IDBAN_PK +" INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +COLUMN_IDKV_FK+" INTEGER,"
//                +COLUMN_TENBAN+" TEXT,"
//                +COLUMN_TRANGTHAIBAN+" INTEGER,"
//                +"FOREIGN KEY ("+COLUMN_IDKV_FK +") REFERENCES "+KHUVUC_TABLE+"("+COLUMN_IDKV_PK+")"
//                +")";
//        String createHoadonTable = "CREATE TABLE IF NOT EXISTS "+HOADON_TABLE
//                +"("+COLUMN_IDHD_PK+" INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +COLUMN_IDTK_FK+" INTEGER,"
//                +COLUMN_IDBAN_FK+" INTEGER,"
//                +COLUMN_TGVAO+" TEXT,"
//                +COLUMN_TGRA+" TEXT,"
//                +COLUMN_TIENTHANHTOAN+" INTEGER,"
//                +COLUMN_TRANGTHAITHANHTOAN+" INTEGER,"
//                +"FOREIGN KEY ("+COLUMN_IDBAN_FK+") REFERENCES "+BANAN_TABLE+"("+COLUMN_IDBAN_PK+"),"
//                +"FOREIGN KEY ("+COLUMN_IDTK_FK+") REFERENCES "+TAIKHOAN_TABLE+"("+COLUMN_IDTK_PK+")"
//                +")";
//        String createCTHDTable = "CREATE TABLE IF NOT EXISTS "+CTHOADON_TABLE
//                +"("+COLUMN_IDCTHD+" INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +COLUMN_IDHD_FK+" INTEGER,"
//                +COLUMN_IDSP_FK+" INTEGER,"
//                +COLUMN_SOLUONG+" INTEGER,"
//                +"FOREIGN KEY ("+COLUMN_IDHD_FK+") REFERENCES "+HOADON_TABLE+"("+COLUMN_IDBAN_PK+"),"
//                +"FOREIGN KEY ("+COLUMN_IDSP_FK+") REFERENCES "+SANPHAM_TABLE+"("+COLUMN_IDSP_PK+")"
//                +")";
//        SQLiteDatabase db = getWritableDatabase();
////        database.execSQL(createKhuVucTable);
////        database.execSQL(createBanAnTable);
//        db.execSQL(createHoadonTable);
//        db.execSQL(createCTHDTable);
////        db.execSQL("DROP TABLE CTHOADON_TABLE");
////        db.execSQL("DROP TABLE HOADON_TABLE");
//        db.close();
    }
    public void ExcuteQuery(String sql)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    //Truy vấn trả kết quả :SELECT
    public Cursor Getdata(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
     //Xu ly tai khoan
     public boolean themTaiKhoan(Taikhoan taikhoan )
     {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_TENTK,taikhoan.getTenTK().toString());
         cv.put(COLUMN_MATKHAU,taikhoan.getMatkhau().toString());
         cv.put(COLUMN_LOAITK,taikhoan.getLoaiTk()+"");
         cv.put(COLUMN_TENNV,taikhoan.getTenNV().toString());
         cv.put(COLUMN_CMND,taikhoan.getCMND().toString());
         cv.put(COLUMN_NGAYSINH,taikhoan.getNgaySinh().toString());
         cv.put(COLUMN_MUCLUONG,taikhoan.getMucLuong()+"");
         cv.put(COLUMN_CHUCVU,taikhoan.getChucVu().toString());

         long check = db.insert(TAIKHOAN_TABLE,null,cv);
         if(check == -1 )
         {
             return false;
         }
         return true;
     }
    public ArrayList<Taikhoan> DanhSachTaiKhoan()
    {
        ArrayList<Taikhoan> listtaikhoan = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TAIKHOAN_TABLE;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String loaitk = cursor.getString(3);
                String tentk = cursor.getString(1);
                String matkhau = cursor.getString(2);
                String ngaysinh = cursor.getString(5);
                String tennv = cursor.getString(4);
                String cmnd = cursor.getString(6);
                String chucVu = cursor.getString(7);
                int mucluong = cursor.getInt(8);
                listtaikhoan.add( new Taikhoan(id,tentk,matkhau,loaitk,tennv,ngaysinh,cmnd,chucVu,mucluong));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listtaikhoan;
    }
     public ArrayList<Taikhoan> layDanhSachTaiKhoan(String idcv)
     {
         ArrayList<Taikhoan> listtaikhoan = new ArrayList<>();
         SQLiteDatabase db = this.getReadableDatabase();
         String sql;
         if(idcv.equals("true"))
         {
             sql = "SELECT * FROM "+TAIKHOAN_TABLE;
         }
         else
         {
             sql = "SELECT * FROM "+TAIKHOAN_TABLE+" WHERE LOAITK ="+idcv;
         }

         Cursor cursor = db.rawQuery(sql,null);
         if(cursor.moveToFirst())
         {
             do {
                 int id = cursor.getInt(0);
                 String tentk = cursor.getString(1);
                 String matkhau = cursor.getString(2);
                 String chucVu = cursor.getString(7);
                 listtaikhoan.add( new Taikhoan(id,tentk,matkhau,chucVu));
             }while (cursor.moveToNext());
         }
         cursor.close();
         db.close();
         return listtaikhoan;
     }
     public void xoaTaiKhoan(int idtk)
     {
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM "+TAIKHOAN_TABLE + " WHERE "+COLUMN_IDTK_PK+"="+idtk);
         db.close();
     }

     //Xu ly khu vuc
    public boolean themkhuVuc(KhuVuc khuvuc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENKV,khuvuc.getTenKhuVuc().toString());
        long insert = db.insert(KHUVUC_TABLE,null,cv);
        if (insert == -1)
        {
            return false;
        }
        return true;
    }
    public boolean xoaKhuVuc(int idkv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(KHUVUC_TABLE,COLUMN_IDKV_PK+"="+idkv,null)>0;
    }
    public boolean capNhatKhuVuc(KhuVuc kv,int idkv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENKV,kv.getTenKhuVuc().toString());
        long update = db.update(KHUVUC_TABLE,cv,COLUMN_IDKV_PK +"="+idkv,null);
        if (update == -1)
        {
            return false;
        }
        return true;
    }

    public ArrayList<KhuVuc> layDanhSachKhuVuc()
    {
        ArrayList<KhuVuc> listKhuVuc = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+KHUVUC_TABLE,null);
        if(cursor.moveToFirst())
        {
            do {
                int idkv = cursor.getInt(0);
                String tenkv = cursor.getString(1);
                listKhuVuc.add(new KhuVuc(idkv,tenkv));
            }while (cursor.moveToNext());
        }
        return listKhuVuc;
    }
    //Xu ly Ban
    public boolean themBan(BanAn banan)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TENBAN,banan.getTenBan());
        cv.put(COLUMN_IDKV_FK,banan.getIdkv());
        cv.put(COLUMN_TRANGTHAIBAN,0);
        long insert = db.insert(BANAN_TABLE,null,cv);
        if(insert == -1)
        {
            return false;
        }
        return true;
    }
    public boolean xoaBan(int IDBAN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(BANAN_TABLE,COLUMN_IDBAN_PK +" = "+IDBAN,null);
        if(delete == -1)
        {
            return false;
        }
        return true;
    }
    public boolean xoaBanbangidkv(int IDKV)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete(BANAN_TABLE,COLUMN_IDKV_FK +"="+IDKV,null);
        if(delete == -1)
        {
            return false;
        }
        return true;
    }
    public boolean capNhapBan(BanAn banan,int IDBAN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IDKV_FK,banan.getIdkv());
        cv.put(COLUMN_TENBAN,banan.getTenBan());
        if(banan.isTrangthai())
        {
            cv.put(COLUMN_TRANGTHAIBAN,1);
        }
        else
        {
            cv.put(COLUMN_TRANGTHAIBAN,0);
        }
        long update = db.update(BANAN_TABLE,cv,COLUMN_IDBAN_PK+"="+IDBAN,null);
        if(update == -1)
        {
            return false;
        }
        return true;
    }
    public ArrayList<BanAn> layDanhSachBan(int IDKV)
    {
        ArrayList<BanAn> listBanAn = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BANAN_TABLE WHERE IDKV_FK = "+IDKV,null);
        if(cursor.moveToFirst())
        {
            do{
                int idban = cursor.getInt(0);
                int idkv = cursor.getInt(1);
                String tenban = cursor.getString(2);
                int trangthai = cursor.getInt(3);
                boolean tt;
                if(trangthai == 1)
                {
                    tt = true;
                }
                else
                {
                    tt = false;
                }
                listBanAn.add(new BanAn(idban,idkv,tenban,tt));
            }while (cursor.moveToNext());
        }
        else{}
        cursor.close();
        db.close();
        return listBanAn;
    }
    public ArrayList<BanAn> DanhSachBan() {
        ArrayList<BanAn> listBanAn = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BANAN_TABLE",null);
        if(cursor.moveToFirst())
        {
            do{
                int idban = cursor.getInt(0);
                int idkv = cursor.getInt(1);
                String tenban = cursor.getString(2);
                int trangthai = cursor.getInt(3);
                boolean tt;
                if(trangthai == 1)
                {
                    tt = true;
                }
                else
                {
                    tt = false;
                }
                listBanAn.add(new BanAn(idban,idkv,tenban,tt));
            }while (cursor.moveToNext());
        }
        else
        {
        }
        cursor.close();
        db.close();
        return listBanAn;
    }
    //Xu ly San Pham
    public int getGiaMon(int idsp)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MONAN_TABLE WHERE IDSP_PK = "+idsp,null);
        if(cursor.moveToFirst())
        {
            return cursor.getInt(3);
        }
        return 0;
    }

    public boolean themSanPham(SanPham sanPham)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IDDM,sanPham.getMaDM());
        cv.put(COLUMN_TENSP,sanPham.getTenMon().toString());
        cv.put(COLUMN_GIASP,sanPham.getGiaMon());
        long insert = db.insert(SANPHAM_TABLE,null,cv);
        if(insert == -1)
        {
            return false;
        }
        return true;
    }
    public ArrayList<SanPham> layDuLieuSanPham()
    {
        ArrayList<SanPham> listsp = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT * FROM "+SANPHAM_TABLE;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do {
                int idsp = cursor.getInt(0);
                int iddm = cursor.getInt(1);
                String tensp = cursor.getString(2);
                int giasp = cursor.getInt(3);

                listsp.add(new SanPham(idsp,iddm,tensp,giasp));
            }while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return listsp;
    }
    public ArrayList<SanPham> layDuLieuSanPhamBangIDDM(String IDDM)
    {
        ArrayList<SanPham> listsp = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT * FROM "+SANPHAM_TABLE+" WHERE "+COLUMN_IDDM+" ="+IDDM;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do {
                int idsp = cursor.getInt(0);
                int iddm = cursor.getInt(1);
                String tensp = cursor.getString(2);
                int giasp = cursor.getInt(3);

                listsp.add(new SanPham(idsp,iddm,tensp,giasp));
            }while (cursor.moveToNext());
        }
        else {}
        cursor.close();
        db.close();
        return listsp;
    }
    public void xoaSanPham(int idspCanXoa)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+SANPHAM_TABLE+" WHERE "+COLUMN_IDSP_PK +"= "+idspCanXoa);
    }
    public void suaSanPham(SanPham sanPham)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+SANPHAM_TABLE+" SET "+COLUMN_TENSP+" = "+"'"+sanPham.getTenMon().toString()+"'"
        +","+COLUMN_GIASP+" = "+sanPham.getGiaMon()
        +","+COLUMN_IDDM+"="+sanPham.getMaDM()
        +" WHERE "+COLUMN_IDSP_PK+" = "+sanPham.getIdMon());

    }

    //Xu ly HoaDon
    public boolean themHoaDon(int idban,int idtk)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IDTK_FK,idtk);
        cv.put(COLUMN_IDBAN_FK,idban);
        cv.put(COLUMN_TIENTHANHTOAN,0);
        cv.put(COLUMN_TRANGTHAITHANHTOAN,false);
        long insert = db.insert(HOADON_TABLE,null,cv);
        if(insert == -1)
        {
            return false;
        }
        return true;
    }
    public ArrayList<Hoadon> DanhSachHoaDonDaThanhToan()
    {
        int idhd,idban,tienthanhtoan;
        String tgvao,tgra,idtk;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Hoadon> arrayListHoaDon = new ArrayList<>();
        String query = "SELECT * FROM "+HOADON_TABLE+" WHERE "+COLUMN_TRANGTHAITHANHTOAN+"="+1;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                idhd = cursor.getInt(0);
                idtk = cursor.getString(1);
                idban = cursor.getInt(2);
                tgvao = cursor.getString(3);
                tgra = cursor.getString(4);
                tienthanhtoan = cursor.getInt(5);
                arrayListHoaDon.add(new Hoadon(idhd,idtk,idban,tgvao,tgra,tienthanhtoan,true));
            }while (cursor.moveToNext());
        }

        return  arrayListHoaDon;
    }
    public ArrayList<Hoadon> DanhSachHoaDonTime(String timein,String timeout)
    {
        int idhd,idban,tienthanhtoan;
        String tgvao,tgra,idtk;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Hoadon> arrayListHoaDon = new ArrayList<>();
        String query = "SELECT * FROM "+HOADON_TABLE+" WHERE "+COLUMN_TRANGTHAITHANHTOAN+"="+1;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                idhd = cursor.getInt(0);
                idtk = cursor.getString(1);
                idban = cursor.getInt(2);
                tgvao = cursor.getString(3);
                tgra = cursor.getString(4);
                tienthanhtoan = cursor.getInt(5);
                arrayListHoaDon.add(new Hoadon(idhd,idtk,idban,tgvao,tgra,tienthanhtoan,true));
            }while (cursor.moveToNext());
        }

        return  arrayListHoaDon;
    }
    public ArrayList<Hoadon> layDanhSachHoaDon()
    {
        int idhd,idban,tienthanhtoan;
        String tgvao,tgra,idtk;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Hoadon> arrayListHoaDon = new ArrayList<>();
        String query = "SELECT * FROM "+HOADON_TABLE+" WHERE "+COLUMN_TRANGTHAITHANHTOAN+"="+1;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                idhd = cursor.getInt(0);
                idtk = cursor.getString(1);
                idban = cursor.getInt(2);
                tgvao = cursor.getString(3);
                tgra = cursor.getString(4);
                tienthanhtoan = cursor.getInt(5);
                arrayListHoaDon.add(new Hoadon(idhd,idtk,idban,tgvao,tgra,tienthanhtoan,true));
            }while (cursor.moveToNext());
        }

        return  arrayListHoaDon;
    }
    public int getidhoadon(int idban)
    {
        int idhd = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HOADON_TABLE WHERE IDBAN_FK = "+idban+" AND TRANGTHAITHANHTOAN = 0",null);
        if(cursor.moveToFirst())
        {
            idhd = cursor.getInt(0);
        }
        return idhd;
    }

    //xu ly CTHD
    public boolean themCTHD(CTHD cthd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IDHD_FK,cthd.getIdhd());
        cv.put(COLUMN_IDSP_FK,cthd.getIdsanpham());
        cv.put(COLUMN_SOLUONG,cthd.getSoluong());
        long insert = db.insert(CTHOADON_TABLE,null,cv);
        if(insert == -1)
        {
            return false;
        }
        return true;
    }
    public ArrayList<CTHD> layThongTinHoaDon(int idhd)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CTHD> listcthd = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CTHOADON_TABLE WHERE IDHD_FK ="+idhd,null);
        if(cursor.moveToFirst())
        {
            do{
                int idcthd = cursor.getInt(0);
                int idsp = cursor.getInt(2);
                int soluong = cursor.getInt(3);
                listcthd.add( new CTHD(idcthd,idhd,idsp,soluong));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listcthd;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
