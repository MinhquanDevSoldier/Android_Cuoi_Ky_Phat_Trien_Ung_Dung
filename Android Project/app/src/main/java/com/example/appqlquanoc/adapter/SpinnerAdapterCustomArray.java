package com.example.appqlquanoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appqlquanoc.R;

public class SpinnerAdapterCustomArray extends BaseAdapter {
    Context context;
    int ResourceLayout;
    String array[];

    public SpinnerAdapterCustomArray(Context context, int resourceLayout, String[] array) {
        this.context = context;
        ResourceLayout = resourceLayout;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.length;
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
        convertView = inflater.inflate(ResourceLayout,null);
        TextView tv = convertView.findViewById(R.id.textviewspinner1);
        tv.setText(array[position]);
        return convertView;
    }
}
