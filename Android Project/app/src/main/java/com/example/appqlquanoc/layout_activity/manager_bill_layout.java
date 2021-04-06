package com.example.appqlquanoc.layout_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewManageBill_Custom_Adapter;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.Hoadon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class manager_bill_layout extends AppCompatActivity {
    TextView edtDATEIN,edtDATEOUT,edtTIMEIN,edtTIMEOUT;
    Button btnLocHoaDon;
    ListView lvDanhSachHoaDon;
    ListViewManageBill_Custom_Adapter adapter;
    DatabaseQuanOc db;
    ArrayList<Hoadon> dshd;
    ArrayList<BanAn> dsban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_bill_layout);
        maping();
        db = new DatabaseQuanOc(manager_bill_layout.this);
        dsban = db.DanhSachBan();
        dshd = db.DanhSachHoaDonDaThanhToan();
        adapter = new ListViewManageBill_Custom_Adapter(getApplicationContext(),R.layout.listvview_manage_bill_row_custom,dshd,dsban);
        lvDanhSachHoaDon.setAdapter(adapter);
        lvDanhSachHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(manager_bill_layout.this,info_bill_layout.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("hoadon",dshd.get(position));
                intent.putExtra("data",bundle);
                startActivityForResult(intent,1212);
            }
        });
        edtDATEIN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDateIn();
            }
        });
        edtDATEOUT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDateOut();
            }
        });
        edtTIMEIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTimeIn();
            }
        });
        edtTIMEOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTimeOut();
            }
        });

        btnLocHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datetimein = edtDATEIN.getText().toString()+" "+edtTIMEIN.getText().toString();
                String datetimeout = edtDATEOUT.getText().toString()+" "+edtTIMEOUT.getText().toString();
                if(datetimein.isEmpty())
                {
                    Toast.makeText(manager_bill_layout.this, "Vui lòng đầy đủ chọn ngày và giờ", Toast.LENGTH_SHORT).show();
                }
                else if (datetimeout.isEmpty())
                {
                    Toast.makeText(manager_bill_layout.this, "Vui lòng đầy đủ chọn ngày và giờ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dshd.clear();
                    dshd.addAll(db.DanhSachHoaDonTime(datetimein,datetimeout));
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void getDateOut()
    {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam  = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDATEOUT.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void getDateIn()
    {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam  = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDATEIN.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void getTimeIn()
    {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0,0,0,hourOfDay,minute);
                edtTIMEIN.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
    private void getTimeOut()
    {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0,0,0,hourOfDay,minute);
                edtTIMEOUT.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
    public void maping()
    {
        btnLocHoaDon = findViewById(R.id.btnLocHoaDon);
        lvDanhSachHoaDon = findViewById(R.id.lvDanhSachHoaDon);
        edtDATEIN = findViewById(R.id.edtDATEIN);
        edtDATEOUT = findViewById(R.id.edtDATEOUT);
        edtTIMEIN = findViewById(R.id.edtTIMEIN);
        edtTIMEOUT = findViewById(R.id.edtTIMEOUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1212 && resultCode == 1213 )
        {
            dshd.clear();
            dshd.addAll(db.DanhSachHoaDonDaThanhToan());
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}