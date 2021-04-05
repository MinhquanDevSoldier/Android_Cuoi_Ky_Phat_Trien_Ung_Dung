package com.example.appqlquanoc.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.layout_activity.Bill_Layout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class UsedTableFragment extends Fragment {
    List<BanAn> listbanan = new ArrayList<>();
    int idkv = 0 ;
    FrameLayout framUsedTable;
    public UsedTableFragment(int makv,ArrayList<BanAn> BANAN) {
        // Required empty public constructor
        this.idkv = makv;
        this.listbanan.addAll(BANAN);
    }
    public void dataBanan() {
        //Add data to listbanan
    }
    public void ThemButtonVaoTable(int top,int left,int i,String trangthai)
    {
        Button btnBan = new Button(this.getContext());
        if(trangthai.equals("Trong"))
        {
            btnBan.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.btnbantrong));
        }
        else if(trangthai.equals("Cokhach"))
        {
            btnBan.setBackground(ContextCompat.getDrawable(this.getContext(),R.drawable.btnbancokhach));
        }
        btnBan.setTextSize(20);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(300,300);
        params.setMargins(left,top,0,0);
        btnBan.setLayoutParams(params);
        btnBan.setId(listbanan.get(i).getIdBan());
        btnBan.setText(listbanan.get(i).getTenBan().toString());
        framUsedTable.addView(btnBan);
        btnBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Bill_Layout.class);
                Bundle bundle = new Bundle();
                BanAn banan = listbanan.get(i);
                bundle.putSerializable("BanAn",banan);
                intent.putExtra("ThongTinBanAn",bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_used_table, container, false);
        dataBanan();

        framUsedTable = (FrameLayout) view.findViewById(R.id.frameUsedTable);
        framUsedTable.removeAllViews();
        int topmargin = 40;
        int leftmargin = 40;
        int checkindex = 1;
        int checkNumberOfTable=0;
        int checkNumberAdd = 0;
        for(int i = 0;i<listbanan.size();i++) {
            if (listbanan.get(i).getIdkv() == idkv) {
                checkNumberAdd++;
                if (checkindex == 1) {
                    if (listbanan.get(i).isTrangthai()) {
                        ThemButtonVaoTable(topmargin, leftmargin, i, "Cokhach");
                    } else {
                        //ThemButtonVaoTable(topmargin, leftmargin, i, "Trong");
                        checkNumberOfTable++;
                    }
                    leftmargin += 340;
                    checkindex++;
                } else if (checkindex == 2) {
                    if (listbanan.get(i).isTrangthai()) {
                        ThemButtonVaoTable(topmargin, leftmargin, i, "Cokhach");
                    } else {
                        //ThemButtonVaoTable(topmargin, leftmargin, i, "Trong");
                        checkNumberOfTable++;
                    }
                    checkindex++;
                    leftmargin += 340;
                } else if (checkindex == 3) {
                    if (listbanan.get(i).isTrangthai()) {
                        ThemButtonVaoTable(topmargin, leftmargin, i, "Cokhach");
                    } else {
                        //ThemButtonVaoTable(topmargin, leftmargin, i, "Trong");
                        checkNumberOfTable++;
                    }
                    leftmargin = 40;
                    topmargin += 340;
                    checkindex = 1;
                }
            }
        }
        if(checkNumberOfTable==checkNumberAdd)
        {
            TextView textViewthongbao = new TextView(getContext());
            textViewthongbao.setText("<Trống>");
            textViewthongbao.setTextSize(30);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1200);
            textViewthongbao.setLayoutParams(params);
            textViewthongbao.setGravity(Gravity.CENTER);
            framUsedTable.addView(textViewthongbao);
        }
        return view;
    }
}