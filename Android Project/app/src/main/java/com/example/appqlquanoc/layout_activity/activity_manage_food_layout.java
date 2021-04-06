package com.example.appqlquanoc.layout_activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
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
import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewProduce_Custom_Adapter;
import com.example.appqlquanoc.adapter.SpinnerAdapterCustomArray;
import com.example.appqlquanoc.doituong.SanPham;

import java.util.ArrayList;

import static com.example.appqlquanoc.R.*;

public class activity_manage_food_layout extends AppCompatActivity {
    EditText edttimkiem;
    Button btntimkiem;
    Button btnlocdm;
    Spinner spdanhmuctp;
    ListView lvproduce;
    ListViewProduce_Custom_Adapter adapterproduce;
    DatabaseQuanOc databaseQuanOc;
    ArrayList<SanPham> arrayListproduce = new ArrayList<>();
    ArrayList<SanPham> arrayListproducefind = new ArrayList<>();

    int iddm,iddmAdd,iddmModify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_manage_food);
        //Cập nhập dữ liệu và ánh xạ
        mapping();
        getDataProduce();


        //Đổ dữ liệu vào spinner
        String arr[] = {"Thức ăn","Đồ uống"};
        SpinnerAdapterCustomArray adapter = new SpinnerAdapterCustomArray(getApplicationContext(), R.layout.spinner_item_custom_by_quan_1,arr);
        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,arr);
        spdanhmuctp.setAdapter(adapter);
        spdanhmuctp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               iddm = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Đổ dữ liệu vào listview
        adapterproduce = new ListViewProduce_Custom_Adapter(getApplicationContext(), R.layout.listviewproduce_row_custom_1,arrayListproduce);
        lvproduce.setAdapter(adapterproduce);

        //Đăng ký ContextMenu cho lvproduce
        registerForContextMenu(lvproduce);

        //Các sự kiện click
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttimkiem.clearFocus();
            }
        });
        btnlocdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_manage_food_layout.this, iddm+"", Toast.LENGTH_SHORT).show();
                arrayListproduce.clear();
                databaseQuanOc = new DatabaseQuanOc(getApplicationContext());
                arrayListproduce.addAll(databaseQuanOc.layDuLieuSanPhamBangIDDM(iddm+""));
                adapterproduce.notifyDataSetChanged();
            }
        });

    }
    //Tạo ConTextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_sua_xoa,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //Bắt sự kiện ContextMenu lvproduce
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int idspselected = arrayListproduce.get(info.position).getIdMon();
        int vitri = info.position;
        switch (item.getItemId())
        {

            case id.menu_suathongtin:
                dialogSuaSanPham(arrayListproduce.get(vitri),vitri);
                Toast.makeText(this, "Sửa produce", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_xoathongtin:
                Toast.makeText(this,idspselected+"", Toast.LENGTH_SHORT).show();
                dialogXoaSanPham(idspselected,vitri);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case id.menuthemfood:
                dialogThemSanPham();
                break;
            case id.menubackmanagefood:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void dialogSuaSanPham(SanPham sanPham,int vitri)
    {
        Dialog dialogSuaSanPham = new Dialog(this);
        dialogSuaSanPham.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSuaSanPham.setContentView(layout.activity_dialog_sua_sanpham);

        Button btnHuySuaSanPham = dialogSuaSanPham.findViewById(id.btnHuySuaSanPham);
        Button btnSuaSanPham = dialogSuaSanPham.findViewById(id.btnSuaSanPham);
        EditText edtTenSanPham_Sua = dialogSuaSanPham.findViewById(id.edtTenSanPham_Sua);
        EditText edtGiaSanPham_Sua = dialogSuaSanPham.findViewById(id.edtGiaSanPham_Sua);

        edtTenSanPham_Sua.setText(sanPham.getTenMon().toString());
        edtGiaSanPham_Sua.setText(sanPham.getGiaMon()+"");

        String arr[]={"Thức ăn","Đồ uống"};
        Spinner spSanPham_Sua = dialogSuaSanPham.findViewById(id.spinnerSanPham_Sua);
        SpinnerAdapterCustomArray adapter = new SpinnerAdapterCustomArray(this, layout.spinner_item_custom_by_quan_1,arr);
        spSanPham_Sua.setAdapter(adapter);
        if(sanPham.getMaDM() == 1)
        {
            spSanPham_Sua.setSelection(0);
        }
        else
        {
            spSanPham_Sua.setSelection(1);
        }
        spSanPham_Sua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iddmModify = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSuaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenspnew = edtTenSanPham_Sua.getText().toString();
                int giaspnew=0;
                try {
                    giaspnew = Integer.parseInt(edtGiaSanPham_Sua.getText().toString());
                }
                catch(Exception exception)
                {
                    Toast.makeText(activity_manage_food_layout.this, "Giá sản phẩm là số nguyên dương", Toast.LENGTH_SHORT).show();
                }


                SanPham sanphamModify = new SanPham(sanPham.getIdMon(),iddmModify,tenspnew,giaspnew);
                arrayListproduce.set(vitri,sanphamModify);
                adapterproduce.notifyDataSetChanged();
                databaseQuanOc = new DatabaseQuanOc(getApplicationContext());
                databaseQuanOc.suaSanPham(sanphamModify);
                dialogSuaSanPham.dismiss();

            }
        });
        btnHuySuaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSuaSanPham.dismiss();
            }
        });
        dialogSuaSanPham.show();
    }
    public int timViTriBangID(int ID)
    {
        for(int i = 0;i<arrayListproduce.size();i++)
        {
            if(arrayListproduce.get(i).getIdMon() == ID)
            {
                return i;
            }
        }
        return -1;
    }

    private void dialogXoaSanPham(int idsp,int vitri)
    {
        AlertDialog.Builder alertXoaSanPham = new AlertDialog.Builder(this);
        int viTriSPCanXoa = timViTriBangID(idsp);
        alertXoaSanPham.setMessage("Xác nhận xóa "+arrayListproduce.get(viTriSPCanXoa).getTenMon());
        alertXoaSanPham.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseQuanOc = new DatabaseQuanOc(getApplicationContext());
                databaseQuanOc.xoaSanPham(idsp);
                arrayListproduce.remove(vitri);
                adapterproduce.notifyDataSetChanged();
            }
        });
        alertXoaSanPham.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertXoaSanPham.show();
    }


    private void dialogThemSanPham()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout.activity_dialog_them_sanpham);

        Button btnthoatmanagefood = (Button) dialog.findViewById(id.btnhuymanagefood);
        Button btnthemmanagefood = (Button) dialog.findViewById(id.btnthemmanagefood);
        EditText edttenmonmanagefood = (EditText) dialog.findViewById(id.edttenmanagefood);
        EditText edtgiamanagefood = (EditText) dialog.findViewById(id.edtgiamanagefood);

        Spinner spmanagefood = (Spinner) dialog.findViewById(id.spinneraddmanagefood);


        String arr[] = {"Thức ăn","Đồ uống"};
        SpinnerAdapterCustomArray adapter = new SpinnerAdapterCustomArray(this, layout.spinner_item_custom_by_quan_2,arr);
        spmanagefood.setAdapter(adapter);
        spmanagefood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iddmAdd = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnthoatmanagefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dialog.getContext(), "Đã hủy", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnthemmanagefood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tenmon = edttenmonmanagefood.getText().toString();
                String gia = edtgiamanagefood.getText().toString();
                if(tenmon.equals(""))
                {
                    Toast.makeText(activity_manage_food_layout.this, "Chưa nhập tên món", Toast.LENGTH_SHORT).show();
                }
                else if(gia.equals("")) {
                    Toast.makeText(activity_manage_food_layout.this, "Chưa nhập giá", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int idmax;
                    if(arrayListproduce.size()==0)
                    {
                        idmax = 0;
                    }
                    else
                    {
                        idmax = arrayListproduce.get(arrayListproduce.size()-1).getIdMon();
                    }

                    SanPham sp = new SanPham(idmax+1,iddmAdd,tenmon, Integer.parseInt(gia));

                    arrayListproduce.add(sp);
                    adapterproduce.notifyDataSetChanged();
                    boolean check = databaseQuanOc.themSanPham(sp);
                    if(check)
                    {
                        Toast.makeText(activity_manage_food_layout.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(activity_manage_food_layout.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void mapping() {
        edttimkiem = findViewById(id.edttimmon);
        btntimkiem = findViewById(id.btntimkiem);
        spdanhmuctp = findViewById(id.spinnerdmtp);
        lvproduce = findViewById(id.lvsanpham);
        btnlocdm = findViewById(id.btnlocdm);
        databaseQuanOc = new DatabaseQuanOc(this);
    }
    public void getDataProduce()
    {
        databaseQuanOc = new DatabaseQuanOc(getApplicationContext());
        arrayListproduce = databaseQuanOc.layDuLieuSanPham();
    }
}