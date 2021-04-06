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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
            TongTien();
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
                        String TimeIn ="";
//                        Date now = Calendar.getInstance().getTime();
//                        TimeIn = now.toString();
                        final Calendar calendar = Calendar.getInstance();
                        int ngay,thang,nam,gio,phut,giay;
                        ngay = calendar.get(Calendar.DATE);
                        thang = calendar.get(Calendar.MONTH);
                        nam = calendar.get(Calendar.YEAR);
                        gio = calendar.get(Calendar.HOUR_OF_DAY);
                        phut = calendar.get(Calendar.MINUTE);
                        giay = calendar.get(Calendar.MILLISECOND);
                        String date,times;
                        if(ngay < 10)
                        {
                            if(thang < 10)
                            {
                                date = "0"+ngay+"/0"+thang+"/"+nam;
                            }
                            else
                            {
                                date = "0"+ngay+"/"+thang+"/"+nam;
                            }
                        }
                        else
                        {
                            if(thang < 10)
                            {
                                date = ngay+"/0"+thang+"/"+nam;
                            }
                            {
                                date = ngay+"/"+thang+"/"+nam;
                            }
                        }
                        if(gio < 10)
                        {
                            if(phut < 10)
                            {
                                times = "0"+gio+":0"+phut+":"+giay;
                            }
                            else
                            {
                                times = "0"+gio+":"+phut+":"+giay;
                            }
                        }
                        else
                        {
                            if(phut < 10)
                            {
                                times = gio+":0"+phut+":"+giay;
                            }
                            else
                            {
                                times = gio+":"+phut+":"+giay;
                            }
                        }
                        TimeIn = date+" "+times;
                        db.ExcuteQuery("UPDATE HOADON_TABLE SET TGVAO = '"+TimeIn+"' WHERE IDHD_PK ="+idhd);
                        db.ExcuteQuery("UPDATE BANAN_TABLE SET TRANGTHAIBAN = 1 WHERE IDBAN_PK ="+banan.getIdBan());
                        Intent intent = new Intent();
                        setResult(RESULT_CODE_SAVE_BILL,intent);
                        Toast.makeText(Bill_Layout.this, "Đã Lưu "+tenBan, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else if(trangthai == 1)
                {
                    ArrayList<CTHD> CTHDgetFromData = new ArrayList<>();
                    CTHDgetFromData.addAll(db.layThongTinHoaDon(idhd));

                    for(int i = 0;i < danhsachCTHD.size();i++ )
                    {
                        if(KiemTraTrungMonDATA(danhsachCTHD.get(i).getIdsanpham(),idhd))
                        {
                            for(int j =0 ;j < CTHDgetFromData.size();j++)
                            {
                                if(danhsachCTHD.get(i).getIdsanpham() == CTHDgetFromData.get(j).getIdsanpham())
                                {
                                    int soluongmoi = danhsachCTHD.get(i).getSoluong();
                                    db.ExcuteQuery("UPDATE CTHOADON_TABLE SET SOLUONG = "+soluongmoi+" WHERE IDHD_FK = "+idhd+" AND IDSP_FK ="+danhsachCTHD.get(i).getIdsanpham());
                                    break;
                                }
                            }
                        }
                        else
                        {
                            db.themCTHD(danhsachCTHD.get(i));
                        }
                    }
                    Toast.makeText(Bill_Layout.this, "Hóa đơn đã cập nhật" + idhd, Toast.LENGTH_SHORT).show();
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
                    String TimeOut ="";
//                        Date now = Calendar.getInstance().getTime();
//                        TimeIn = now.toString();
                    final Calendar calendar = Calendar.getInstance();
                    int ngay,thang,nam,gio,phut,giay;
                    ngay = calendar.get(Calendar.DATE);
                    thang = calendar.get(Calendar.MONTH);
                    nam = calendar.get(Calendar.YEAR);
                    gio = calendar.get(Calendar.HOUR_OF_DAY);
                    phut = calendar.get(Calendar.MINUTE);
                    giay = calendar.get(Calendar.MILLISECOND);
                    String date,times;
                    if(ngay < 10)
                    {
                        if(thang < 10)
                        {
                            date = "0"+ngay+"/0"+thang+"/"+nam;
                        }
                        else
                        {
                            date = "0"+ngay+"/"+thang+"/"+nam;
                        }
                    }
                    else
                    {
                        if(thang < 10)
                        {
                            date = ngay+"/0"+thang+"/"+nam;
                        }
                        {
                            date = ngay+"/"+thang+"/"+nam;
                        }
                    }
                    if(gio < 10)
                    {
                        if(phut < 10)
                        {
                            times = "0"+gio+":0"+phut+":"+giay;
                        }
                        else
                        {
                            times = "0"+gio+":"+phut+":"+giay;
                        }
                    }
                    else
                    {
                        if(phut < 10)
                        {
                            times = gio+":0"+phut+":"+giay;
                        }
                        else
                        {
                            times = gio+":"+phut+":"+giay;
                        }
                    }
                    TimeOut = date+" "+times;
                    db.ExcuteQuery("UPDATE HOADON_TABLE SET TIENTHANHTOAN = "+tvTongTien.getText().toString()+" WHERE IDHD_PK ="+idhd);
                    db.ExcuteQuery("UPDATE HOADON_TABLE SET TGRA ='"+TimeOut+"' WHERE IDHD_PK ="+idhd);
                    db.ExcuteQuery("UPDATE HOADON_TABLE SET TRANGTHAITHANHTOAN = 1 WHERE IDHD_PK ="+idhd);
                    db.ExcuteQuery("UPDATE BANAN_TABLE SET TRANGTHAIBAN = 0 WHERE IDBAN_PK ="+banan.getIdBan());
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
        idhd = db.getidhoadon(banan.getIdBan());
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.menu_xoathongtin)
        {
            int idsp;
            idsp = danhsachCTHD.get(info.position).getIdsanpham();
            Toast.makeText(this, info.position+"", Toast.LENGTH_SHORT).show();
            danhsachCTHD.remove(info.position);
            db.ExcuteQuery("DELETE FROM CTHOADON_TABLE WHERE IDHD_FK = "+idhd+" AND IDSP_FK ="+idsp);
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
                            //kiem tra xem mon co chua
                            if(KiemTraTrungMon(listSP.get(position).getIdMon(),idhd) == true)
                            {
                                for(int i = 0; i< danhsachCTHD.size();i++)
                                {
                                    if(listSP.get(position).getIdMon() == danhsachCTHD.get(i).getIdsanpham())
                                    {
                                        soluong += danhsachCTHD.get(i).getSoluong();
                                        danhsachCTHD.set(i,new CTHD(-1,idhd,listSP.get(position).getIdMon(),soluong));
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                danhsachCTHD.add(new CTHD(-1,idhd,listSP.get(position).getIdMon(),soluong));
                            }
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
                //TongTien();
                adapterOrder.notifyDataSetChanged();
                Toast.makeText(Bill_Layout.this, "Đóng thêm món", Toast.LENGTH_SHORT).show();
                dialogThemMon.dismiss();
//                try {
                    TongTien();
//                }
//                catch (Exception ex)
//                {
//                    Toast.makeText(Bill_Layout.this, ex+"", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        //
        dialogThemMon.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                adapterOrder.notifyDataSetChanged();
                TongTien();
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
    //Hàm kiểm tra trùng lặp món trong listorder
    public boolean KiemTraTrungMon(int idsp,int idhd)
    {
        for(int i = 0; i< danhsachCTHD.size();i++)
        {
            if(idsp == danhsachCTHD.get(i).getIdsanpham())
            {
                return true;
            }
        }
        return false;
    }
    public boolean KiemTraTrungMonDATA(int idsp,int idhd)
    {
        ArrayList<CTHD> CTHDgetFromData = new ArrayList<>();
        CTHDgetFromData.addAll(db.layThongTinHoaDon(idhd));
        for(int i = 0; i< CTHDgetFromData.size();i++)
        {
            if(idsp == CTHDgetFromData.get(i).getIdsanpham())
            {
                return true;
            }
        }
        return false;
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
    public void TongTien()
    {
        int tongtien = 0;
        for(int i = 0 ;i < danhsachCTHD.size();i++)
        {
            tongtien+=danhsachCTHD.get(i).getSoluong()*db.getGiaMon(danhsachCTHD.get(i).getIdsanpham());
        }
        tvTongTien.setText(tongtien+"");
    }


}