package com.example.appqlquanoc.layout_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.Taikhoan;

import java.util.ArrayList;

public class account_preview extends AppCompatActivity {
    DatabaseQuanOc db;
    EditText edttennv,edtchucvu,edtcmnd,edtsdt,edtngaysinh,edtmucluong;
    Button btnCapNhat,btnXacNhanCapNhat;
    String phanloaitk;
    int idtk;
    ArrayList<Taikhoan> danhsachtk = new ArrayList<>();
    Taikhoan tttk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_preview);
        mapping();
        Intent intent = getIntent();
        phanloaitk=intent.getStringExtra("loaitk");
        idtk = intent.getIntExtra("idtk",1);

        loadThongTinTk();

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttennv.setEnabled(true);
                edtcmnd.setEnabled(true);
                edtsdt.setEnabled(true);
                edtmucluong.setEnabled(true);
                edtngaysinh.setEnabled(true);
                edtchucvu.setEnabled(true);
                btnXacNhanCapNhat.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btn_dang_nhap));
                btnXacNhanCapNhat.setEnabled(true);
            }
        });
        btnXacNhanCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(account_preview.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                btnXacNhanCapNhat.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btn_dang_nhap_2));
                btnXacNhanCapNhat.setEnabled(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //loadThongTinTk();
        btnXacNhanCapNhat.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btn_dang_nhap_2));
        btnXacNhanCapNhat.setEnabled(false);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account_preview,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_ql_taikhoan:
                if(phanloaitk.equals("1"))
                {
                    Intent intent = new Intent(this.getApplicationContext(), acitvity_manage_account_layout.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Mục này dành cho quản lý", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_trolai_managetk:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void mapping()
    {
        edttennv = findViewById(R.id.edttennv);
        edtchucvu = findViewById(R.id.edtchucvu);
        edtsdt = findViewById(R.id.edtsodienthoai);
        edtcmnd = findViewById(R.id.edtcmndnv);
        edtngaysinh = findViewById(R.id.edtngaysinhnv);
        edtmucluong = findViewById(R.id.edtmucluongnv);
        btnCapNhat = findViewById(R.id.btnCapNhatTT);
        btnXacNhanCapNhat = findViewById(R.id.btnXacNhanCapNhatTT);
        db = new DatabaseQuanOc(getApplicationContext());
    }
    public void loadThongTinTk()
    {
        danhsachtk.addAll(db.DanhSachTaiKhoan());
        if(danhsachtk.size() != 0)
        {
            for(int i= 0;i<danhsachtk.size();i++)
            {
                if(danhsachtk.get(i).getIDTK()==idtk)
                {
                    tttk = danhsachtk.get(i);
                    break;
                }
            }
            if(tttk != null)
            {
                edttennv.setText(tttk.getTenNV().toString());
                edtcmnd.setText(tttk.getCMND().toString());
                edtsdt.setText("Đang nâng cấp");
                edtmucluong.setText(tttk.getMucLuong()+"");
                edtngaysinh.setText("Đang nâng cấp");
                edtchucvu.setText(tttk.getChucVu().toString());
            }
        }
    }

}