package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.SanPham;

import java.util.ArrayList;

public class ListViewThemOrder_Custom_Adapter extends BaseAdapter {
    Context context;
    int LayoutResource;
    ArrayList<SanPham> arrayList;
    int iddm;

    public ListViewThemOrder_Custom_Adapter(Context context, int layoutResource, ArrayList<SanPham> arrayList) {
        this.context = context;
        LayoutResource = layoutResource;
        this.arrayList = arrayList;
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

        TextView tvproduce = convertView.findViewById(R.id.textviewProduce);
        tvproduce.setText(arrayList.get(position).getTenMon()+"\n"+arrayList.get(position).getGiaMon());
        return convertView;
    }
}
