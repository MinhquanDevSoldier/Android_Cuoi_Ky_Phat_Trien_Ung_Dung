package com.example.appqlquanoc.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.Taikhoan;

import java.util.ArrayList;

public class ListViewAccount_Custom_Adapter extends BaseAdapter
{
    Context context;
    int LayoutResource;
    ArrayList<Taikhoan> arrayList;

    public ListViewAccount_Custom_Adapter(Context context, int layoutResource, ArrayList<Taikhoan> arrayList) {
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

        TextView tvIDTaiKhoan = convertView.findViewById(R.id.textview_IdAccount);
        TextView tvNameTaiKhoan = convertView.findViewById(R.id.textview_NameAccount);
        TextView tvChucvu = convertView.findViewById(R.id.textview_TypeAccount);
        tvIDTaiKhoan.setText(arrayList.get(position).getIDTK()+"");
        tvNameTaiKhoan.setText(arrayList.get(position).getTenTK().toString());
        tvChucvu.setText(arrayList.get(position).getChucVu().toString());
        return convertView;
    }
}