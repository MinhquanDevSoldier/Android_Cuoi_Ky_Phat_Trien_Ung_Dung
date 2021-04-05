package com.example.appqlquanoc.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.BlockedNumberContract;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.layout_activity.Bill_Layout;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.layout_activity.MainLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AllTableFragment extends Fragment {
    final static int REQUEST_CODE_SELECT_TABLE = 1401;
    ArrayList<BanAn> listbanan = new ArrayList<>();
    int idkv = 0 ;
    ArrayList<BanAn> banan = new ArrayList<>();
    FrameLayout frameAllTable;
    public AllTableFragment(int idkv,ArrayList<BanAn> BANAN) {
        // Required empty public constructor
        this.idkv = idkv;
        this.banan.addAll(BANAN);
    }
    public void dataBanan() {
        //Add data to listbanan
        //listbanan.add(new BanAn(1,1,"Bàn 1A",false));

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
        frameAllTable.addView(btnBan);
        btnBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((MainLayout)getActivity()).chonban(listbanan.get(i));

                    //startActivity(intent);
                }catch (Exception ex)
                {
                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_table, container, false);
        dataBanan();
        listbanan.addAll(banan);

        frameAllTable = (FrameLayout) view.findViewById(R.id.frameAllTable);
        frameAllTable.removeAllViews();
        int topmargin = 40;
        int leftmargin = 40;
        int checkindex = 1;
        for(int i = 0;i<listbanan.size();i++)
        {
            if(listbanan.get(i).getIdkv() == idkv)
            {
                if(checkindex == 1)
                {
                    if(listbanan.get(i).isTrangthai())
                    {
                        ThemButtonVaoTable(topmargin,leftmargin,i,"Cokhach");
                    }
                    else
                    {
                        ThemButtonVaoTable(topmargin,leftmargin,i,"Trong");

                    }
                    leftmargin +=340;
                    checkindex++;
                }
                else if(checkindex == 2)
                {
                    if(listbanan.get(i).isTrangthai())
                    {
                        ThemButtonVaoTable(topmargin,leftmargin,i,"Cokhach");
                    }
                    else
                    {
                        ThemButtonVaoTable(topmargin,leftmargin,i,"Trong");

                    }
                    checkindex++;
                    leftmargin+=340;
                }
                else if(checkindex == 3)
                {
                    if(listbanan.get(i).isTrangthai())
                    {
                        ThemButtonVaoTable(topmargin,leftmargin,i,"Cokhach");
                    }
                    else
                    {
                        ThemButtonVaoTable(topmargin,leftmargin,i,"Trong");

                    }
                    leftmargin = 40;
                    topmargin+=340;
                    checkindex = 1;
                }
            }
        }
        return  view;
    }


}