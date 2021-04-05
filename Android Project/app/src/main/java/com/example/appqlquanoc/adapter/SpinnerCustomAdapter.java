package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.KhuVuc;
import com.example.appqlquanoc.layout_activity.MainLayout;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCustomAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    ArrayList<KhuVuc> arraylist;

    public SpinnerCustomAdapter(Context context, int myLayout, ArrayList<KhuVuc> arraylist) {
        this.context = context;
        this.myLayout = myLayout;
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
        convertView = inflater.inflate(myLayout,null);
        TextView tv = (TextView) convertView.findViewById(R.id.textviewspinner1);
        tv.setText(arraylist.get(position).getTenKhuVuc().toString());
        return convertView;
    }
}
