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
import android.widget.Toast;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.SanPham;
import com.example.appqlquanoc.layout_activity.Bill_Layout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {
    FrameLayout frameLayoutMonAn;
    ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    public FoodFragment(ArrayList<SanPham> sanPham) {
        // Required empty public constructor
        sanPhamArrayList.addAll(sanPham);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        //------------------------------------------------------------------------------------------
        frameLayoutMonAn = view.findViewById(R.id.frameSelectFood);
        int topmargin=20;
        for(int i = 0; i < sanPhamArrayList.size() ; i++)
        {
            if(sanPhamArrayList.get(i).getMaDM() == 1)
            {
                Button buttonMonan = new Button(getContext());
                buttonMonan.setTextSize(30);
                buttonMonan.setTypeface(null, Typeface.BOLD);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400);
                params.setMargins(20,topmargin,20,0);
                buttonMonan.setLayoutParams(params);
                buttonMonan.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.btnmonan));
                buttonMonan.setText(sanPhamArrayList.get(i).getTenMon()+"\n"+ sanPhamArrayList.get(i).getGiaMon());
                frameLayoutMonAn.addView(buttonMonan);
                topmargin+=420;
                buttonMonan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            ((Bill_Layout)getActivity()).theMonVaoOrder();
                        }catch (Exception ex)
                        {
                            Toast.makeText(getActivity(), ex+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        }
        return view;
    }
}