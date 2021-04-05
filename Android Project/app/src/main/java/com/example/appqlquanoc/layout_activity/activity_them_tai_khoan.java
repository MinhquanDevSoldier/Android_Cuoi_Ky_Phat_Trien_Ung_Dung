package com.example.appqlquanoc.layout_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.SpinnerAdapterCustomArray;
import com.example.appqlquanoc.doituong.Taikhoan;

public class activity_them_tai_khoan extends AppCompatActivity {
    private final static int themtaikhoanresult = 14032;
    String arrChucVuforQuanly[]={"Nhân viên"};
    String arrChucVuforChuQuan[]={"Nhân viên","Quản lý"};
    EditText edtTenTK_Them,edtMatKhau_Them,edtTenNhanVien_Them,edtMucLuong_Them,edtCMND_Them;
    Button btnThemTaiKhoan,btnHuyThemTK;
    Spinner spChucVu;
    DatabaseQuanOc databaseQuanOc;

    int chucvu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_khoan);

        mapping();

        SpinnerAdapterCustomArray adapterCustomArray = new SpinnerAdapterCustomArray(getApplicationContext(),R.layout.spinner_item_custom_by_quan_1,arrChucVuforChuQuan);
        spChucVu.setAdapter(adapterCustomArray);
        spChucVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chucvu = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnThemTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themTaiKhoan();
            }
        });
        btnHuyThemTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void themTaiKhoan()
    {
        String tenChucVu = "";
        if(chucvu == 0)
        {
            tenChucVu ="Nhân viên";
        }
        else if(chucvu == 1)
        {
            tenChucVu = "Quản lý";
        }
        String tentk_them,matkhau_them,tennv_them,cmnd_them;
        String mucluong_them,ngaysinh_them="";

        tentk_them = edtTenTK_Them.getText().toString();
        matkhau_them = edtMatKhau_Them.getText().toString();
        tennv_them = edtTenNhanVien_Them.getText().toString();
        cmnd_them = edtCMND_Them.getText().toString();
        mucluong_them = edtMucLuong_Them.getText().toString();
        ngaysinh_them = "";

        if(tentk_them.isEmpty())
        {
            Toast.makeText(this, "Chưa nhập tên tài khoản", Toast.LENGTH_SHORT).show();
        }
        else if(matkhau_them.isEmpty())
        {
            Toast.makeText(this, "Chưa  nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else if(tennv_them.isEmpty())
        {
            Toast.makeText(this, "Chưa nhập tên nhân viên", Toast.LENGTH_SHORT).show();
        }
//        else if(ngaysinh_them.isEmpty())
//        {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//        }
        else if(cmnd_them.isEmpty())
        {
            Toast.makeText(this, "Chưa nhập Chứng Minh Nhân Dân", Toast.LENGTH_SHORT).show();
        }
        else if(mucluong_them.isEmpty())
        {
            Toast.makeText(this, "Chưa nhập mức lương", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Taikhoan tk = new Taikhoan();
            tk.setTenTK(tentk_them);
            tk.setMatkhau(matkhau_them);
            tk.setLoaiTk(chucvu+"");
            tk.setTenNV(tennv_them);
            tk.setNgaySinh(ngaysinh_them);
            tk.setCMND(cmnd_them);
            tk.setMucLuong(Integer.parseInt(mucluong_them));
            tk.setChucVu(tenChucVu);
            boolean check = databaseQuanOc.themTaiKhoan(tk);
            if(check == true)
            {
                Intent intent = new Intent();
                setResult(themtaikhoanresult,intent);
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                finish();
            }

        }


    }

    public void mapping()
    {
        spChucVu = findViewById(R.id.spinner_ChucVu_them);
        btnHuyThemTK = findViewById(R.id.btnHuyThemTaiKhoan);
        edtTenNhanVien_Them  = findViewById(R.id.edtTenNhanVien);
        edtCMND_Them = findViewById(R.id.edtCMND);
        edtMucLuong_Them = findViewById(R.id.edtMucLuong_Them);
        btnThemTaiKhoan = findViewById(R.id.btnThemTaiKhoan);
        edtTenTK_Them = findViewById(R.id.edtTenTk_Them);
        edtMatKhau_Them = findViewById(R.id.edtMatKhau_Them);
        databaseQuanOc = new DatabaseQuanOc(activity_them_tai_khoan.this);
    }
}