package com.example.appqlquanoc.adapter;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appqlquanoc.doituong.BanAn;
import com.example.appqlquanoc.fragment.AllTableFragment;
import com.example.appqlquanoc.fragment.EmptyTableFragment;
import com.example.appqlquanoc.fragment.UsedTableFragment;

import java.util.ArrayList;

public class ViewPagerSelectTable extends FragmentStatePagerAdapter {
    private final static int CODE_RESULT_QL_BAN=113113;
    ArrayList<BanAn> banan = new ArrayList<>();
    int makv = 0;
    public ViewPagerSelectTable(@NonNull FragmentManager fm, int behavior, int idkv, ArrayList<BanAn> BANAN) {
        super(fm, behavior);
        if(idkv != 0)
        {
            this.banan.addAll(BANAN);
            this.makv = idkv;
        }
        else
        {
            Toast.makeText(null,"Loi tai Adapter",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
//        if (object instanceof AllTableFragment) {
//            // Create a new method notifyUpdate() in your fragment
//            // it will get call when you invoke
//            // notifyDatasetChaged();
//            ((AllTableFragment) object).notifyUpdate();
//        }
        return POSITION_NONE;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new AllTableFragment(makv,banan);
                //return new FoodFragment();
            case 1:
                return new UsedTableFragment(makv,banan);
            case 2:
                return new EmptyTableFragment(makv,banan);
            default:
                return new AllTableFragment(makv,banan);
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position)
        {
            case 0:
                title = "Tất cả";
                break;
            case 1:
                title = "Có khách";
                break;
            case 2:
                title = "Còn trống";
                break;
        }
        return title;
    }



}
