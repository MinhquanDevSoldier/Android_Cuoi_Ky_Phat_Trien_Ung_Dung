package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.KhuVuc;
import com.example.appqlquanoc.layout_activity.activity_manage_area;

import java.util.ArrayList;

public class ListViewArea_Custom_Adapter extends BaseAdapter {
    Context context;
    int LayoutResource;
    ArrayList<KhuVuc> arrayList;

    public ListViewArea_Custom_Adapter(Context context, int layoutResource, ArrayList<KhuVuc> arrayList) {
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
        TextView tvKhuVuc = convertView.findViewById(R.id.textview_KhuVuc);
        tvKhuVuc.setText(arrayList.get(position).getIdkv()+"\n"+arrayList.get(position).getTenKhuVuc());
        return convertView;
    }
}
