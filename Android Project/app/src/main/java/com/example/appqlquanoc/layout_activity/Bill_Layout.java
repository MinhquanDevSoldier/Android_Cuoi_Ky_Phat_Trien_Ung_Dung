package com.example.appqlquanoc.layout_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appqlquanoc.ChangeTable;
import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ListViewOrder_Custom_Adapter;
import com.example.appqlquanoc.adapter.ListViewThemOrder_Custom_Adapter;
import com.example.appqlquanoc.adapter.SpinnerAdapterCustomArray;
import com.example.appqlquanoc.adapter.ViewPagerSelectFoodAdapter;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.CTHD;
import com.example.appqlquanoc.doituong.SanPham;
import com.google.android.material.tabs.TabLayout;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

import javax.crypto.AEADBadTagException;

public class Bill_Layout extends AppCompatActivity {
    final static int RESULT_CODE_SAVE_BILL = 1402;
    final static int RESULT_CODE_PAY_THE_BILL = 1403;
    DatabaseQuanOc db;
    //Khởi tạo các biến hóa đơn
    TextView tvtenBan,tvTongTien;
    Button btnLuuHoaDon,btnThanhToanHD;
    ListView lvThongTinHoaDon;
    BanAn banan;
    String tenBan;
    ListViewOrder_Custom_Adapter adapterOrder;
    ArrayList<CTHD> danhsachCTHD = new ArrayList<>();
    ArrayList<SanPham> danhsachSP = new ArrayList<>();
    ArrayList<SanPham> listSP = new ArrayList<>();
    int idhd,idtk;
    int trangthai;
    CTHD cthd;
    //int soluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_layout);
        //Ánh xạ các biến và dữ liệu
        mapping();
        danhsachCTHD.clear();
        danhsachSP = db.layDuLieuSanPham();
        //layThongTinHoaDon();

        //Lấy dữ liệu từ trang trước đó (activity_main_layout.xml)
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ThongTinBanAn");

        if(bundle != null)
        {
            idtk = bundle.getInt("idtk");
            banan = (BanAn) bundle.getSerializable("BanAn");
            //gán vào textview tên bàn
            tenBan = banan.getTenBan().toString();
            tvtenBan.setText(banan.getTenBan().toString());
            if(banan.isTrangthai())
            {
                trangthai = 1;
            }
            else {
                trangthai = 0;
            }
        }
        tvtenBan.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btnbantrong));


        if(trangthai == 1)
        {
            idhd= db.getidhoadon(banan.getIdBan());
            danhsachCTHD.addAll(db.layThongTinHoaDon(idhd));
        }
        else
        {
            boolean themhd = db.themHoaDon(banan.getIdBan(),idtk);
            if(themhd == true)
            {
                idhd= db.getidhoadon(banan.getIdBan());
            }
        }
        //load CTHD
        adapterOrder = new ListViewOrder_Custom_Adapter(getApplicationContext(),R.layout.listvieworder_row_custom,danhsachCTHD,danhsachSP);
        lvThongTinHoaDon.setAdapter(adapterOrder);
        registerForContextMenu(lvThongTinHoaDon);
//        lvThongTinHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                return false;
//            }
//        });
        //Các sự kiện click
        btnLuuHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalorder;
                totalorder= lvThongTinHoaDon.getCount();
                Toast.makeText(Bill_Layout.this, totalorder+"", Toast.LENGTH_SHORT).show();
                if(trangthai == 0)
                {
                    if(totalorder == 0)
                    {
                        Toast.makeText(Bill_Layout.this, "Không thể lưu do chưa có món trong Order", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        for(int i = 0;i < danhsachCTHD.size();i++ )
                        {
                            db.themCTHD(danhsachCTHD.get(i));
                        }
                        db.ExcuteQuery("UPDATE BANAN_TABLE SET TRANGTHAIBAN = 1 WHERE IDBAN_PK ="+banan.getIdBan());
                        Intent intent = new Intent();
                        setResult(RESULT_CODE_SAVE_BILL,intent);
                        Toast.makeText(Bill_Layout.this, "Đã Lưu "+tenBan, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else if(trangthai == 1)
                {
                    Toast.makeText(Bill_Layout.this, "Hóa đơn đã cập nhật", Toast.LENGTH_SHORT).show();
                    finish();
                }



            }
        });
        btnThanhToanHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idhd = db.getidhoadon(banan.getIdBan());
                if(banan.isTrangthai())
                {
                    db.ExcuteQuery("UPDATE BANAN_TABLE SET TRANGTHAIBAN = 0 WHERE IDBAN_PK ="+banan.getIdBan());
                    db.ExcuteQuery("UPDATE HOADON_TABLE SET TRANGTHAITHANHTOAN = 1 WHERE IDHD_PK ="+idhd);
                    setResult(RESULT_CODE_PAY_THE_BILL,intent);
                    Toast.makeText(Bill_Layout.this, "Đã Thanh Toán "+tenBan, Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(Bill_Layout.this, "Hãy lưu hóa đơn trước khi thanh toán", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu_sua_xoa,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.menu_xoathongtin)
        {
            Toast.makeText(this, info.position+"", Toast.LENGTH_SHORT).show();
            danhsachCTHD.remove(info.position);
            db.ExcuteQuery("DELETE FROM CTHOADON_TABLE WHERE IDHD_FK = 8 AND IDSP_FK = 11");
            adapterOrder.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bill,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Xử lý sự kiện chọn OptionMenu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.menuCancelBill:
                if(banan.isTrangthai())
                {
                    Toast.makeText(this, "Đã hủy", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Chưa có hóa đơn", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menuback:
                finish();
                break;
            case R.id.menuthemmon:
                hienThiDialogThemMon();
                break;
            case R.id.menudoiban:
                intent = new Intent(getApplicationContext(), ChangeTable.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hienThiDialogThemMon() {
        Dialog dialogThemMon = new Dialog(this);
        dialogThemMon.setContentView(R.layout.dialog_them_order);
        //
        Spinner spThemOrder = dialogThemMon.findViewById(R.id.spinnerThemOrder);
        ListView lvThemOrder = dialogThemMon.findViewById(R.id.listviewThemOrder);
        Button btndong = dialogThemMon.findViewById(R.id.btndongThemOrder);

        String Array[]={"Thức ăn","Đồ uống"};
        SpinnerAdapterCustomArray adaptersp = new SpinnerAdapterCustomArray(getApplicationContext(),R.layout.spinner_item_custom_by_quan_1,Array);
        spThemOrder.setAdapter(adaptersp);


        listSP.addAll(db.layDuLieuSanPhamBangIDDM(spThemOrder.getSelectedItemPosition()+1+""));
        ListViewThemOrder_Custom_Adapter adapterlv = new ListViewThemOrder_Custom_Adapter(getApplicationContext(),R.layout.listviewthemorder_row_custom_1,listSP);
        lvThemOrder.setAdapter(adapterlv);
        spThemOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listSP.clear();
                listSP.addAll(db.layDuLieuSanPhamBangIDDM(position+1+""));
                adapterlv.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btndong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemMon.dismiss();
            }
        });
        lvThemOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Bill_Layout.this, listSP.get(position).getTenMon(), Toast.LENGTH_SHORT).show();
                Dialog dialog = new Dialog(dialogThemMon.getContext());
                dialog.setContentView(R.layout.dialog_xac_nhan_them_order);

                TextView tvProduceOrder = dialog.findViewById(R.id.tvProduceOrder);
                Button btnsub = dialog.findViewById(R.id.btnsubnumber);
                Button btnadd = dialog.findViewById(R.id.btnaddnumber);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAcceptOrder =dialog.findViewById(R.id.btnAcceptOrder);
                Button btnUnAcceptOrder = dialog.findViewById(R.id.btnUnAcceptOrder);


                tvProduceOrder.setText(listSP.get(position).getTenMon().toString());
                btnsub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int soluong = Integer.parseInt(edtNumber.getText().toString());
                        if(soluong > 0)
                        {
                            soluong -=1;
                            edtNumber.setText(soluong+"");
                        }
                        else {
                            Toast.makeText(Bill_Layout.this, "Đã đạt tối thiểu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int soluong = Integer.parseInt(edtNumber.getText().toString());
                        if(soluong < 99)
                        {
                            soluong +=1;
                            edtNumber.setText(soluong+"");
                        }
                        else {
                            Toast.makeText(Bill_Layout.this, "Đã đạt tối đa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnAcceptOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idhd = db.getidhoadon(banan.getIdBan());
                        int soluong = Integer.parseInt(edtNumber.getText().toString());
                        if(soluong > 0 && soluong <= 99)
                        {
                            danhsachCTHD.add(new CTHD(-1,idhd,listSP.get(position).getIdMon(),soluong));
                            Toast.makeText(Bill_Layout.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(Bill_Layout.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnUnAcceptOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        btndong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterOrder.notifyDataSetChanged();
                Toast.makeText(Bill_Layout.this, "Đóng thêm món", Toast.LENGTH_SHORT).show();
                dialogThemMon.dismiss();
            }
        });
        //
        dialogThemMon.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                adapterOrder.notifyDataSetChanged();
                Toast.makeText(Bill_Layout.this, "Đóng thêm món", Toast.LENGTH_SHORT).show();
            }
        });
        dialogThemMon.show();
    }

    //Hàm ánh xạ các control
    public void mapping()
    {
        btnLuuHoaDon = (Button) findViewById(R.id.buttonLuuHoaDon);
        btnThanhToanHD = (Button) findViewById(R.id.buttonThanhToanHoaDon);
        tvtenBan = (TextView) findViewById(R.id.textviewTenBan);
        tvTongTien = (TextView) findViewById(R.id.textviewTongTien);
        lvThongTinHoaDon = (ListView) findViewById(R.id.listviewThongTinHoaDon);
        db = new DatabaseQuanOc(getApplicationContext());

    }
    //Hàm lấy dữ liệu tương ứng với bàn
    public void layThongTinHoaDon()
    {
        adapterOrder = new ListViewOrder_Custom_Adapter(getApplicationContext(),R.layout.listvieworder_row_custom,danhsachCTHD,danhsachSP);
        lvThongTinHoaDon.setAdapter(adapterOrder);
    }
    //opendialog them mon
    public void theMonVaoOrder()
    {
        Toast.makeText(this, "dialog them mon", Toast.LENGTH_SHORT).show();
    }
    public void reloadlistview()
    {
        adapterOrder.notifyDataSetChanged();
    }

}