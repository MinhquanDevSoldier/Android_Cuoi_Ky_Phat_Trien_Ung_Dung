package com.example.appqlquanoc.fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.SanPham;
import com.example.appqlquanoc.layout_activity.Bill_Layout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class DrinksFragment extends Fragment {

    FrameLayout frameLayoutDouong;
    ArrayList<SanPham> listdrinks = new ArrayList<>();
    public DrinksFragment(ArrayList<SanPham> sanPham) {
        // Required empty public constructor
        listdrinks.addAll(sanPham);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);

        frameLayoutDouong = view.findViewById(R.id.frameselectdrinks);
        //
        int topmargin = 20;
        for(int i=0 ;i < listdrinks.size();i++)
        {
            if(listdrinks.get(i).getMaDM() == 2)
            {
                Button btndouong = new Button(getContext());
                btndouong.setTypeface(null, Typeface.BOLD);
                btndouong.setTextSize(30);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400);
                params.setMargins(20,topmargin,20,0);
                btndouong.setLayoutParams(params);
                btndouong.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.btndouong));
                btndouong.setText(listdrinks.get(i).getTenMon()+"\n"+listdrinks.get(i).getGiaMon());
                frameLayoutDouong.addView(btndouong);
                topmargin+=420;
                btndouong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((Bill_Layout)getActivity()).theMonVaoOrder();
                    }
                });
            }
        }
        return view;
    }
}
