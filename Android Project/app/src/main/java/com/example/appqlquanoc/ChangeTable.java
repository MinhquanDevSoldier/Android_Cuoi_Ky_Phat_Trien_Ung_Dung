package com.example.appqlquanoc;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlquanoc.adapter.SpinnerCustomAdapter;
import com.example.appqlquanoc.doituong.KhuVuc;

import java.util.ArrayList;

public class ChangeTable extends AppCompatActivity {
    //Khởi tạo các biến
    Spinner spKhuVucDoiBan;
    ArrayList<KhuVuc> listKhuVucDoiBan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_table);
        //Ánh xạ các biến và dữ liệu
        mapping();
        layThongTinKhuVucVaBan();

        //gán dự liệu vào spinner
        listKhuVucDoiBan.add(new KhuVuc(0,"Khu A"));
        listKhuVucDoiBan.add(new KhuVuc(1,"Khu B"));
        listKhuVucDoiBan.add(new KhuVuc(2,"Khu C"));
        SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(getApplicationContext(),R.layout.spinner_item_custom_by_quan,listKhuVucDoiBan);
        spKhuVucDoiBan.setAdapter(adapter);
    }
    //Hàm ánh xạ
    public void mapping()
    {
        spKhuVucDoiBan = (Spinner) findViewById(R.id.spinnerKhuVucDoiBan);
    }
    //Hàm tải dữ liệu bàn và khu vực
    public void layThongTinKhuVucVaBan()
    {}



}