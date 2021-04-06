package com.example.appqlquanoc.layout_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewOrder_Custom_Adapter;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.CTHD;
import com.example.appqlquanoc.doituong.Hoadon;
import com.example.appqlquanoc.doituong.SanPham;
import com.example.appqlquanoc.doituong.Taikhoan;

import java.util.ArrayList;

public class info_bill_layout extends AppCompatActivity {
    TextView tvtenBanInfoBill,tvTongTienInfoBill,tvNhanVienOrder;
    Button btnXoaHoaDon;
    ListView lvThongTinHoaDonInfoBill;
    DatabaseQuanOc db;
    ArrayList<CTHD> danhsachCTHD = new ArrayList<>();
    ArrayList<SanPham> danhsachSP = new ArrayList<>();
    ListViewOrder_Custom_Adapter adapterBillInfo;
    int idhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_bill_layout);
        maping();
        Intent getdata = getIntent();
        Bundle bundle = getdata.getBundleExtra("data");
        Hoadon hd = (Hoadon) bundle.getSerializable("hoadon");
        idhd = hd.getIdhd();
        ArrayList<BanAn> dsban = new ArrayList<>();
        for(int i = 0 ;i < dsban.size();i++)
        {
            if(hd.getIdban() == dsban.get(i).getIdBan())
            {
                tvtenBanInfoBill.setText(dsban.get(i).getTenBan().toString());
                break;
            }
        }
        ArrayList<Taikhoan> dstaikhoan = new ArrayList<>();
        dstaikhoan.addAll(db.DanhSachTaiKhoan());
        for(int i = 0 ; i< dstaikhoan.size();i++)
        {
            if(Integer.parseInt(hd.getIdtk()) == dstaikhoan.get(i).getIDTK())
            {
                tvNhanVienOrder.setText(dstaikhoan.get(i).getTenNV());
            }
        }
        danhsachCTHD.addAll(db.layThongTinHoaDon(hd.getIdhd()));
        danhsachSP.addAll(db.layDuLieuSanPham());
        TongTien();
        //load CTHD
        adapterBillInfo = new ListViewOrder_Custom_Adapter(getApplicationContext(),R.layout.listvieworder_row_custom,danhsachCTHD,danhsachSP);
        lvThongTinHoaDonInfoBill.setAdapter(adapterBillInfo);
        TongTien();
        btnXoaHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaHoaDon(idhd);
            }
        });
    }
    private void XoaHoaDon(int idhd)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Bạn muốn xóa hóa đơn này ?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.ExcuteQuery("DELETE from HOADON_TABLE WHERE IDHD_PK = "+idhd);
                setResult(1213);
                finish();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void maping() {
        tvNhanVienOrder = findViewById(R.id.tvNhanVienOrder);
        btnXoaHoaDon = (Button) findViewById(R.id.btnXoaHoaDon);
        tvtenBanInfoBill = (TextView) findViewById(R.id.textviewTenBanInfoBill);
        tvTongTienInfoBill = (TextView) findViewById(R.id.textviewTongTienInfoBill);
        lvThongTinHoaDonInfoBill = (ListView) findViewById(R.id.listviewThongTinHoaDonInfoBill);
        db = new DatabaseQuanOc(this);
    }
    public void TongTien()
    {
        int tongtien = 0;
        for(int i = 0 ;i < danhsachCTHD.size();i++)
        {
            tongtien+=danhsachCTHD.get(i).getSoluong()*db.getGiaMon(danhsachCTHD.get(i).getIdsanpham());
        }
        tvTongTienInfoBill.setText(tongtien+"");
    }
}