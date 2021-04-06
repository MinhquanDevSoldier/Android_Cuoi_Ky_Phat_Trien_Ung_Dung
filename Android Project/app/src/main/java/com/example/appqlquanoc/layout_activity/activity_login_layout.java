package com.example.appqlquanoc.layout_activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.SanPham;
import com.example.appqlquanoc.doituong.Taikhoan;

import java.util.ArrayList;

public class activity_login_layout extends AppCompatActivity {
    //Initialize variable
    DatabaseQuanOc databaseQuanOc;
    Button btn_DangNhap;
    Switch switch_HienMatKhau;
    EditText edt_TenTK,edt_MatKhau;
    ArrayList<Taikhoan> listTK = new ArrayList<>();
    int idtk;
    //Initialize variabl to get info login
    String tenTK,matKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        databaseQuanOc = new DatabaseQuanOc(activity_login_layout.this);
        khoi_Tao_DatabaseTaiKhoan();
        //Assign variable
        Anhxa();

        edt_MatKhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //hide or show password
        switch_HienMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    edt_MatKhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    edt_MatKhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //Initialize array list of Taikhoan
        //Check type of Taikhoan
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenTK = edt_TenTK.getText().toString();
                matKhau = edt_MatKhau.getText().toString();
                if(tenTK.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Chưa nhập tên Tài khoản",Toast.LENGTH_SHORT).show();
                }
                else
                if(matKhau.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Chưa nhập mật khẩu",Toast.LENGTH_SHORT).show();
                }
                else
                if(kt_TaiKhoan(tenTK,matKhau))
                {
                    for(int i =0;i<listTK.size();i++)
                    {
                        if(listTK.get(i).getTenTK().equals(tenTK))
                        {
                            idtk = listTK.get(i).getIDTK();
                            break;
                        }
                    }
                    if(kt_Loai_TaiKhoan(tenTK,matKhau)==1)
                    {
                        try {
                            Intent mainintent;
                            mainintent = new Intent(activity_login_layout.this, MainLayout.class);
                            mainintent.putExtra("loaitk","1");
                            mainintent.putExtra("idtk",idtk+"");
                            startActivity(mainintent);
                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công\nQuyền : Quản lý",Toast.LENGTH_SHORT).show();
                        }catch (Exception ex)
                        {
                            Toast.makeText(activity_login_layout.this, ex.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                    else
                    {
                        Intent mainIntent = new Intent(activity_login_layout.this,MainLayout.class);
                        mainIntent.putExtra("loaitk","0");
                        mainIntent.putExtra("idtk",idtk+"");
                        startActivity(mainIntent);
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công\nQuyền : Nhân viên",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sai tên tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void khoi_Tao_DatabaseTaiKhoan() {
        listTK = databaseQuanOc.layDanhSachTaiKhoan("true");
    }

    public void Anhxa()
    {
        switch_HienMatKhau = findViewById(R.id.Switch_Hienthi_MatKhau);
        btn_DangNhap = findViewById(R.id.button_DangNhap);
        edt_TenTK = (EditText) findViewById(R.id.EditText_TenTK);
        edt_MatKhau = findViewById(R.id.EditText_MatKhau);
    }
    public boolean kt_TaiKhoan(String tentk,String matKhau)
    {
        for(int i = 0;i<listTK.size();i++)
        {
            if(tenTK.equals(listTK.get(i).getTenTK()))
            {
                if(matKhau.equals(listTK.get(i).getMatkhau()))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public int kt_Loai_TaiKhoan(String tentk,String matKhau)
    {
        for(int i = 0;i<listTK.size();i++)
        {
            if(tenTK.equals(listTK.get(i).getTenTK().toString()))
            {
                if(matKhau.equals(listTK.get(i).getMatkhau().toString()))
                {
                    if(listTK.get(i).getChucVu().equals("Quản lý"))
                    {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}