package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.SanPham;

import java.util.ArrayList;

public class ListViewProduce_Custom_Adapter extends BaseAdapter {
    Context context;
    int LayoutResource;
    ArrayList<SanPham> arraylist = new ArrayList<>();

    public ListViewProduce_Custom_Adapter(Context context, int layoutResource, ArrayList<SanPham> arraylist) {
        this.context = context;
        LayoutResource = layoutResource;
        this.arraylist = arraylist;
    }
    @Override
    public int getCount() {
        return arraylist.size();
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

        TextView tvidproduce = convertView.findViewById(R.id.textview_idproduce);
        TextView tvnameproduce = convertView.findViewById(R.id.textview_nameproduce);
        TextView tvpriceproduce = convertView.findViewById(R.id.textview_priceproduce);
        try
        {
            tvidproduce.setText(arraylist.get(position).getIdMon()+"");
            tvnameproduce.setText(arraylist.get(position).getTenMon()+"");
            tvpriceproduce.setText(arraylist.get(position).getGiaMon()+"");
        }catch (Exception ex)
        {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }
}
