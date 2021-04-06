package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.Hoadon;

import java.util.ArrayList;

public class ListViewManageBill_Custom_Adapter extends BaseAdapter {

    Context context;
    int LayoutResource;
    ArrayList<Hoadon> arrayList;
    ArrayList<BanAn> arrayListBan;

    public ListViewManageBill_Custom_Adapter(Context context, int layoutResource, ArrayList<Hoadon> arrayList,ArrayList<BanAn> arrayListBan) {
        this.context = context;
        LayoutResource = layoutResource;
        this.arrayList = arrayList;
        this.arrayListBan = arrayListBan;
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

        TextView tvIdHoaDon = convertView.findViewById(R.id.tvIdHoaDon);
        TextView tvTenBan = convertView.findViewById(R.id.tvTenBanTheoHoaDon);
        TextView tvTongTien = convertView.findViewById(R.id.tvTongTienHoaDon);
        TextView tvDateTimeIn = convertView.findViewById(R.id.tvDateTimeIn);
        TextView tvDateTimeOut = convertView.findViewById(R.id.tvDateTimeOut);

        tvIdHoaDon.setText(arrayList.get(position).getIdhd()+"");
        tvTongTien.setText(arrayList.get(position).getTongTien()+"");
        for(int i = 0 ;i<arrayListBan.size();i++)
        {
            if(arrayList.get(position).getIdban() == arrayListBan.get(i).getIdBan())
            {
                tvTenBan.setText(arrayListBan.get(i).getTenBan());
                break;
            }
        }
        tvDateTimeIn.setText(arrayList.get(position).getTgvao());
        tvDateTimeOut.setText(arrayList.get(position).getTgra());
        return convertView;
    }
}
