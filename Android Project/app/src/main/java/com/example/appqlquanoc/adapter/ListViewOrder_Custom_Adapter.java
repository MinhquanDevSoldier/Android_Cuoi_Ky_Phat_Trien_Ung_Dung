package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.CTHD;
import com.example.appqlquanoc.doituong.SanPham;

import java.util.ArrayList;

public class ListViewOrder_Custom_Adapter extends BaseAdapter {
    Context context;
    int LayoutResource;
    ArrayList<CTHD> arrayList;
    ArrayList<SanPham> arrayListSP;
    DatabaseQuanOc db;

    public ListViewOrder_Custom_Adapter(Context context, int layoutResource, ArrayList<CTHD> arrayList,ArrayList<SanPham> arrayListSP) {
        this.context = context;
        LayoutResource = layoutResource;
        this.arrayList = arrayList;
        this.arrayListSP = arrayListSP;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(LayoutResource,null);

        TextView tvtenmon = convertView.findViewById(R.id.textviewTenMonOrder);
        TextView tvsoluong = convertView.findViewById(R.id.textviewSoLuongOrder);
        TextView tvthanhtien = convertView.findViewById(R.id.textviewThanhTienOrder);
        int idsp,giasp,soluong;
        idsp = arrayList.get(position).getIdsanpham();
        giasp = 0;
        soluong = arrayList.get(position).getSoluong();
        String tensp="";
        for(int i = 0 ;i<arrayListSP.size();i++)
        {
            if(arrayListSP.get(i).getIdMon() == idsp)
            {
                tensp = arrayListSP.get(i).getTenMon().toString();
                giasp = arrayListSP.get(position).getGiaMon();
            }
        }

        tvtenmon.setText(tensp);
        tvsoluong.setText(soluong+"");
        tvthanhtien.setText(soluong*giasp+"");
        return convertView;
    }
}
