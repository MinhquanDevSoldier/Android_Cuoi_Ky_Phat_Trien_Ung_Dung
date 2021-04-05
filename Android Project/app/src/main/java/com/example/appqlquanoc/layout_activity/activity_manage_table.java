package com.example.appqlquanoc.layout_activity;

import android.app.Dialog;
import android.content.Intent;
import android.icu.number.IntegerWidth;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewTable_Custom_Adapter;
import com.example.appqlquanoc.adapter.SpinnerCustomAdapter;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.KhuVuc;

import java.util.ArrayList;

public class activity_manage_table extends AppCompatActivity {
    private final static int CODE_RESULT_QL_BAN=1132;
    private final static int QUAN_LY_KHUVUC_RQ = 14321;
    private final static int QUAN_LY_KHUVUC_RS = 14322;
    //Khai báo các biến toàn cục
    Button buttonLocTheoKhuVuc,buttonTimKiemBan;
    EditText editTextTimKiemBan;
    Spinner spinnerKhuVuc;
    ListView listViewDanhSachBan;
    DatabaseQuanOc databaseQuanOc;
    ArrayList<KhuVuc> listKhuVuc = new ArrayList<>();
    ArrayList<BanAn> listDanhSachBan = new ArrayList<>();
    ArrayList<BanAn> listDanhSachBanTimKiem = new ArrayList<>();
    SpinnerCustomAdapter adapterkv;
    ListViewTable_Custom_Adapter adapterlvkv;
    int sppositon;
    int vitrisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_table_layout);
        //gọi hàm ánh xạ và khởi tạo dự liệu
        mapping();
        getDataBan();


            adapterkv = new SpinnerCustomAdapter(activity_manage_table.this,R.layout.spinner_item_custom_by_quan_1,listKhuVuc);
            spinnerKhuVuc.setAdapter(adapterkv);
            spinnerKhuVuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sppositon = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            adapterlvkv = new ListViewTable_Custom_Adapter(activity_manage_table.this,R.layout.listviewtable_row_custom_2,listDanhSachBan);
            listViewDanhSachBan.setAdapter(adapterlvkv);

        registerForContextMenu(listViewDanhSachBan);

        buttonLocTheoKhuVuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idkv = listKhuVuc.get(sppositon).getIdkv();
                listDanhSachBan.clear();
                listDanhSachBan.addAll(databaseQuanOc.layDanhSachBan(idkv));
                adapterlvkv.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_them_ban:
                ThemBan();
                break;
            case R.id.menu_ql_khuvuc:
                Intent intent = new Intent(activity_manage_table.this,activity_manage_area.class);
                startActivityForResult(intent,QUAN_LY_KHUVUC_RQ);
                //startActivity(intent);
                break;
            case R.id.menu_tro_lai_managetable:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int idban = listDanhSachBan.get(info.position).getIdBan();
        switch (item.getItemId())
        {
            case R.id.menu_suathongtin:
                CapNhatBan(idban,info.position);
                break;
            case R.id.menu_xoathongtin:
                boolean check = databaseQuanOc.xoaBan(idban);
                if(check == true)
                {
                    Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    listDanhSachBan.remove(info.position);
                }

                adapterlvkv.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void CapNhatBan(int idban,int vitri)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_capnhat_ban);

        Spinner spKhuVucCapNhatBan = dialog.findViewById(R.id.spinnerKhuVucCapNhapBan);
        Button btnCapNhatBan = dialog.findViewById(R.id.btnCapNhatBan);
        Button btnHuyCapNhatBan = dialog.findViewById(R.id.btnHuyCapNhatBan);
        EditText edtTenBanCapNhat = dialog.findViewById(R.id.textview_tenBan_capnhat);

        SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(getApplicationContext(),R.layout.spinner_item_custom_by_quan_1,listKhuVuc);
        spKhuVucCapNhatBan.setAdapter(adapter);
        spKhuVucCapNhatBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vitrisp = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnHuyCapNhatBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCapNhatBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idban = listDanhSachBan.get(vitri).getIdBan();
                int idkv = listKhuVuc.get(vitrisp).getIdkv();
                boolean trangthai = listDanhSachBan.get(vitri).isTrangthai();
                String tenban = edtTenBanCapNhat.getText().toString();
                BanAn ba = new BanAn(idban,idkv,tenban,trangthai);
                boolean update = databaseQuanOc.capNhapBan(ba,idban);
                if(update == true)
                {
                    listDanhSachBan.set(vitri,ba);
                    Toast.makeText(activity_manage_table.this, "Da cap nhat", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(activity_manage_table.this, "Cap nhat that bai", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
        Toast.makeText(this, "idban : "+idban+"\nPosition : "+vitri, Toast.LENGTH_SHORT).show();
    }

    public void ThemBan()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_ban);


        Spinner spthemban = dialog.findViewById(R.id.spinnerKhuVucThemBan);
        Button btnThemBan = dialog.findViewById(R.id.btnThemBan);
        Button btnHuyThemBan = dialog.findViewById(R.id.btnHuyThemBan);
        EditText edtThemBan = dialog.findViewById(R.id.textview_TenBan_Them);

        SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(getApplicationContext(),R.layout.spinner_item_custom_by_quan_1,listKhuVuc);
        spthemban.setAdapter(adapter);
        spthemban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vitrisp = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenban = edtThemBan.getText().toString();
                int idkv = listKhuVuc.get(vitrisp).getIdkv();
                int idban;
                if(listDanhSachBan.size()-1<0)
                {
                    idban = 0;
                }
                else
                {
                    idban = listDanhSachBan.get(listDanhSachBan.size()-1).getIdBan()+1;
                }

                boolean themban = databaseQuanOc.themBan(new BanAn(-1,idkv,tenban,false));
                if(themban == true)
                {
                    listDanhSachBan.add(new BanAn(idban,idkv,tenban,false));
                    Toast.makeText(activity_manage_table.this, "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
                }
                adapterlvkv.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btnHuyThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_manage_table.this, "Đã hủy", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });



        dialog.show();
        Toast.makeText(this, "Thêm bàm", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_table,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu_sua_xoa,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //Hàm ánh xạ (English : mapping)
    public void mapping()
    {
        buttonLocTheoKhuVuc = (Button) findViewById(R.id.btnLocKhuVuc);
        buttonTimKiemBan = (Button) findViewById(R.id.btnTimKiemBan);
        editTextTimKiemBan = (EditText) findViewById(R.id.edtTimBan);
        spinnerKhuVuc = (Spinner) findViewById(R.id.spinnerKhuVucQL);
        listViewDanhSachBan = (ListView) findViewById(R.id.lvDanhSachBan);
        databaseQuanOc  = new DatabaseQuanOc(getApplicationContext());
    }
    //Hàm khởi tạo dữ liệu
    public void getDataBan()
    {

        //listKhuVuc.add(new KhuVuc(1,"Khu A"));
        listKhuVuc.addAll(databaseQuanOc.layDanhSachKhuVuc());
        if(listKhuVuc.isEmpty())
        {
            listKhuVuc.add(new KhuVuc(-1,"Hãy thêm khu vực"));
        }
        int idkv = listKhuVuc.get(sppositon).getIdkv();
        //listDanhSachBan = databaseQuanOc.layDanhSachBan(idkv);
        listDanhSachBan = databaseQuanOc.DanhSachBan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == QUAN_LY_KHUVUC_RQ && resultCode == QUAN_LY_KHUVUC_RS)
        {
            listKhuVuc.clear();
            listKhuVuc.addAll(databaseQuanOc.layDanhSachKhuVuc());
            adapterkv.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(CODE_RESULT_QL_BAN,intent);
        super.onBackPressed();
    }
}