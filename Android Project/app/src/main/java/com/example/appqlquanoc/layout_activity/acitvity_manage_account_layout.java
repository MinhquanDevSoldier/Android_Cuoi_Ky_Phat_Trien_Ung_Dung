package com.example.appqlquanoc.layout_activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewAccount_Custom_Adapter;
import com.example.appqlquanoc.adapter.SpinnerAdapterCustomArray;
import com.example.appqlquanoc.doituong.Taikhoan;

import java.util.ArrayList;

public class acitvity_manage_account_layout extends AppCompatActivity {
    private final static int themtaikhoanrequest = 14031;
    private final static int themtaikhoanresult = 14032;
    Button btnLocChucVu;
    Spinner spinnerChucVu;
    ListView lvTaiKhoan;
    ArrayList<Taikhoan> danhSachTK = new ArrayList<>();
    String arrchucvu[] = {"Tất cả","Quản lý","Nhân viên"};
    DatabaseQuanOc databaseQuanOc = new DatabaseQuanOc(acitvity_manage_account_layout.this);
    ListViewAccount_Custom_Adapter adapterAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitvity_manage_account_layout);
        layDanhSachTaiKhoan();
        mapping();

        SpinnerAdapterCustomArray adapterChucVu = new SpinnerAdapterCustomArray(getApplicationContext(),R.layout.spinner_item_custom_by_quan_1,arrchucvu);
        spinnerChucVu.setAdapter(adapterChucVu);

        adapterAccount = new ListViewAccount_Custom_Adapter(getApplicationContext(),R.layout.activity_list_view_account__custom__adapter,danhSachTK);
        lvTaiKhoan.setAdapter(adapterAccount);
        registerForContextMenu(lvTaiKhoan);
        btnLocChucVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idcv;
                idcv = spinnerChucVu.getSelectedItemPosition()+"";
                if(idcv.equals(0+""))
                {
                    idcv ="true";
                }
                if(idcv.equals(1+""))
                {
                    idcv = "1";
                }
                if(idcv.equals(2+""))
                {
                    idcv = "0";
                }
                danhSachTK.clear();
                danhSachTK.addAll(databaseQuanOc.layDanhSachTaiKhoan(idcv));
                adapterAccount.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int idtk=0;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        idtk = danhSachTK.get(info.position).getIDTK();
        if(item.getItemId() == R.id.menuXoaTaiKhoan)
        {
            xoaTaiKhoan(idtk);
            danhSachTK.remove(info.position);
            adapterAccount.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
public void xoaTaiKhoan(int idtk)
{
    databaseQuanOc.xoaTaiKhoan(idtk);
}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_modify_account,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void layDanhSachTaiKhoan()
    {
        danhSachTK = databaseQuanOc.layDanhSachTaiKhoan("true");
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_them_tai_khoan:
                Toast.makeText(this, "Thêm tài khoản", Toast.LENGTH_SHORT).show();
                moGiaoDienThemTk();

                break;
            case R.id.thoat_qltk:
                finish();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void moGiaoDienThemTk()
    {
        Intent intentThemTaiKhoan = new Intent(acitvity_manage_account_layout.this,activity_them_tai_khoan.class);
        try {
            startActivityForResult(intentThemTaiKhoan,themtaikhoanrequest);
        }catch (Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

        //startActivity(intentThemTaiKhoan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_account,menu);
        return super.onCreateOptionsMenu(menu);

    }
    public void mapping()
    {
        btnLocChucVu = findViewById(R.id.btnLocChucVu);
        spinnerChucVu = findViewById(R.id.spinner_ChucVu);
        lvTaiKhoan = findViewById(R.id.lvDanhSacTaiKhoan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == themtaikhoanrequest && resultCode == themtaikhoanresult && data!=null )
        {
            danhSachTK.clear();
            danhSachTK.addAll(databaseQuanOc.layDanhSachTaiKhoan("true"));
            adapterAccount.notifyDataSetChanged();
        }
    }
}
