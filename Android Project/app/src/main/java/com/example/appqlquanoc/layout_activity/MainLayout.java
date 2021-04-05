package com.example.appqlquanoc.layout_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.SpinnerCustomAdapter;
import com.example.appqlquanoc.adapter.ViewPagerSelectTable;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.KhuVuc;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainLayout extends AppCompatActivity {
    private final static int CODE_REQUEST_QL_BAN=1131;
    private final static int CODE_RESULT_QL_BAN=1132;
    //Khởi các các requestcode và resultcode
    final static int REQUEST_CODE_SELECT_TABLE = 1401;
    final static int RESULT_CODE_SAVE_BILL = 1402;
    final static int RESULT_CODE_PAY_THE_BILL = 1403;
    //Khởi tạo các biến trong Layout
    ArrayList<KhuVuc> listkhuvuc = new ArrayList<>();
    ArrayList<BanAn> listbanan = new ArrayList<>();
    TabLayout tabBanAn;
    ViewPager viewPagerBanAn;
    Spinner spinnerKhvuc;
    String loaitk;
    String idtk;
    SpinnerCustomAdapter arrayAdapter;
    DatabaseQuanOc databaseQuanOc = new DatabaseQuanOc(this);
    ViewPagerSelectTable adapter;
    int sppositon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Anhxa();
        databasetable();

        Intent intent = getIntent();
        loaitk =  intent.getStringExtra("loaitk");
        idtk = intent.getStringExtra("idtk");

        kt_QuyenSD(loaitk);
        //
        arrayAdapter = new SpinnerCustomAdapter(getApplicationContext(), R.layout.spinner_item_custom_by_quan_1,listkhuvuc);
        spinnerKhvuc.setAdapter(arrayAdapter);

        spinnerKhvuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainLayout.this, listkhuvuc.get(position).getTenKhuVuc(), Toast.LENGTH_SHORT).show();
                sppositon = position;

                listbanan = databaseQuanOc.layDanhSachBan(listkhuvuc.get(position).getIdkv());
                adapter = new ViewPagerSelectTable(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,listkhuvuc.get(position).getIdkv(),listbanan);
                viewPagerBanAn.setAdapter(adapter);
                tabBanAn.setupWithViewPager(viewPagerBanAn);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //loadban();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quanlyquanoc,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.menukhuvucvaban:
                if(loaitk.equals("0"))
                {
                    Toast.makeText(this, "Mục dành cho quản lý", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent = new Intent(getApplicationContext(), activity_manage_table.class);
                    startActivityForResult(intent,CODE_REQUEST_QL_BAN);
                }
                break;
            case R.id.menuthucpham:
                if(loaitk.equals("0"))
                {
                    Toast.makeText(this, "Mục dành cho quản lý", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent = new Intent(getApplicationContext(), activity_manage_food_layout.class);
                    startActivity(intent);
                }
                break;
            case R.id.menuhoadon:
                Toast.makeText(this, "Mã tài khoản :"+idtk, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menutaikhoan:
                intent = new Intent(getApplicationContext(), account_preview.class);
                intent.putExtra("loaitk",loaitk);
                intent.putExtra("idtk",Integer.parseInt(idtk));
                startActivity(intent);
                break;
            case R.id.menudangnhap:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void databasetable()
    {
        //Add data to listkhuvuc
       listkhuvuc = databaseQuanOc.layDanhSachKhuVuc();

    }
    private void kt_QuyenSD(String loaitk) {

        if(loaitk.equals("1"))
        {
            Toast.makeText(getApplicationContext(),"Đăng nhập thành công\n" +
                    "vai trò : Quản lý",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Đăng nhập thành công\n" +
                    "vai trò : Nhân viên",Toast.LENGTH_SHORT).show();
        }
    }
//
    public void chonban(BanAn banan)
    {
        Toast.makeText(this, banan.getTenBan().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Bill_Layout.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("BanAn",banan);
        bundle.putInt("idtk",Integer.parseInt(idtk));
        intent.putExtra("ThongTinBanAn",bundle);
        startActivityForResult(intent,REQUEST_CODE_SELECT_TABLE);
    }

    public void Anhxa()
    {
        spinnerKhvuc = findViewById(R.id.spinnerkhuvuc);
        tabBanAn = findViewById(R.id.tablayoutBanan);
        viewPagerBanAn = findViewById(R.id.viewpagerBanan);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Chọn menu > Đăng nhập", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODE_REQUEST_QL_BAN && resultCode == CODE_RESULT_QL_BAN)
        {
            loadban();
            listkhuvuc.clear();
            listkhuvuc.addAll(databaseQuanOc.layDanhSachKhuVuc());
            arrayAdapter.notifyDataSetChanged();
        }
        if(requestCode == REQUEST_CODE_SELECT_TABLE && resultCode == RESULT_CODE_SAVE_BILL )
        {
            loadban();
        }
        if(requestCode == REQUEST_CODE_SELECT_TABLE && resultCode == RESULT_CODE_PAY_THE_BILL )
        {
            loadban();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void loadban()
    {
        listbanan.clear();
        listbanan = databaseQuanOc.layDanhSachBan(listkhuvuc.get(sppositon).getIdkv());
        adapter = new ViewPagerSelectTable(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,listkhuvuc.get(sppositon).getIdkv(),listbanan);
        viewPagerBanAn.setAdapter(adapter);
        tabBanAn.setupWithViewPager(viewPagerBanAn);
    }

}