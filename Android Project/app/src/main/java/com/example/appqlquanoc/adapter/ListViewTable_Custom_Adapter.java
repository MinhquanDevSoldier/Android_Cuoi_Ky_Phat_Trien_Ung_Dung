package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.doituong.KhuVuc;
import com.example.appqlquanoc.layout_activity.activity_manage_table;

import java.util.ArrayList;

public class ListViewTable_Custom_Adapter extends BaseAdapter {
    Context context;
    int LayoutResource;
    ArrayList<BanAn> arrayList;

    public ListViewTable_Custom_Adapter(Context context, int layoutResource, ArrayList<BanAn> arrayList) {
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
        TextView tvidban = convertView.findViewById(R.id.textview_idtable);
        TextView tvBan = convertView.findViewById(R.id.textview_nametable);
        tvidban.setText(arrayList.get(position).getIdBan()+"");
        tvBan.setText(arrayList.get(position).getTenBan().toString());
        return convertView;
    }
}
