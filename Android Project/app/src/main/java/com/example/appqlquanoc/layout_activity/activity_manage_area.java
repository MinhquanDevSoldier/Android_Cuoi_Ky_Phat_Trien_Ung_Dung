package com.example.appqlquanoc.layout_activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewArea_Custom_Adapter;
import com.example.appqlquanoc.doituong.KhuVuc;

import java.util.ArrayList;

public class activity_manage_area extends AppCompatActivity {
    private final static int QUAN_LY_KHUVUC_RS = 14322;
    ListView lvKhuvuc;

    ArrayList<KhuVuc> danhsachkv = new ArrayList<>();
    DatabaseQuanOc databaseQuanOc = new DatabaseQuanOc(activity_manage_area.this);
    ListViewArea_Custom_Adapter adapterarea;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_area);
        mapping();

        adapterarea = new ListViewArea_Custom_Adapter(activity_manage_area.this,R.layout.listviewarea_row_custom_1,danhsachkv);
        lvKhuvuc.setAdapter(adapterarea);
        registerForContextMenu(lvKhuvuc);

    }
    public void mapping()
    {
        lvKhuvuc = findViewById(R.id.lvDanhSachKhuVuc);
        danhsachkv = databaseQuanOc.layDanhSachKhuVuc();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu_sua_xoa,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage_area,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int idkv = danhsachkv.get(info.position).getIdkv();
        switch (item.getItemId())
        {
            case R.id.menu_suathongtin:
                CapNhatKhuVuc(idkv,info.position);
                break;
            case R.id.menu_xoathongtin:
                boolean check = databaseQuanOc.xoaKhuVuc(idkv);
                if(check == true)
                {
                    Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    danhsachkv.remove(info.position);
                }

                adapterarea.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuThemKhuVuc:
//                databaseQuanOc.QueryData("drop table KHUVUC_TABLE");
//                databaseQuanOc.QueryData("DROP TABLE BANAN_TABLE");
                ThemKhuVuc();
                break;
            case R.id.menuThoatQuanLyKhuVuc:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void CapNhatKhuVuc(int idkv,int vitri)
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_capnhat_khuvuc);

        EditText edtTenKV = dialog.findViewById(R.id.textview_tenkv_capnhat);
        Button btnCapNhatkV = dialog.findViewById(R.id.btnCapNhatKV);
        Button btnHuyCapNhatKV = dialog.findViewById(R.id.btnHuyCapNhatKV);

        btnHuyCapNhatKV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCapNhatkV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkv = edtTenKV.getText().toString();
                if(tenkv.isEmpty())
                {
                    Toast.makeText(activity_manage_area.this, "Chưa nhập tên Khu Vực", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    int idkv = danhsachkv.get(danhsachkv.size()-1).getIdkv();
                    databaseQuanOc.capNhatKhuVuc(new KhuVuc(-1,tenkv),idkv);
                    danhsachkv.set(vitri,new KhuVuc(danhsachkv.get(vitri).getIdkv(),tenkv));
                    adapterarea.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }
    public void ThemKhuVuc()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_khuvuc);

        EditText edtTenKV = dialog.findViewById(R.id.textview_TenKV_Them);
        Button btnThemkV = dialog.findViewById(R.id.btnThemKV);
        Button btnHuyThemKV = dialog.findViewById(R.id.btnHuyThemKV);

        btnHuyThemKV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThemkV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkv = edtTenKV.getText().toString();
                if(tenkv.isEmpty())
                {
                    Toast.makeText(activity_manage_area.this, "Chưa nhập tên Khu Vực", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    int idkv;
                    if(danhsachkv.size() == 0)
                    {
                        idkv=-1;
                    }
                    else
                    {
                        idkv = danhsachkv.get(danhsachkv.size()-1).getIdkv();
                    }

                    boolean check = databaseQuanOc.themkhuVuc(new KhuVuc(idkv+1,tenkv));
                    if(check == true)
                    {
                        Toast.makeText(activity_manage_area.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        danhsachkv.add(new KhuVuc(idkv+1,tenkv));
                    }

                    adapterarea.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(QUAN_LY_KHUVUC_RS,intent);
        finish();
    }
}
