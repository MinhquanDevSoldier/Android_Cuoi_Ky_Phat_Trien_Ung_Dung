package com.example.appqlquanoc.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appqlquanoc.doituong.SanPham;
import com.example.appqlquanoc.fragment.DrinksFragment;
import com.example.appqlquanoc.fragment.FoodFragment;

import java.util.ArrayList;

public class ViewPagerSelectFoodAdapter extends FragmentStatePagerAdapter {
    ArrayList<SanPham> arrlistsp = new ArrayList<>();
    public ViewPagerSelectFoodAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<SanPham> listsp) {
        super(fm, behavior);
        arrlistsp.addAll(listsp);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                FoodFragment foodFragment = new FoodFragment(arrlistsp);
                return foodFragment;
            case 1:
                return  new DrinksFragment(arrlistsp);
            default:
                return new FoodFragment(arrlistsp);
        }
    }
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position)
        {
            case 0:
                title = "Thức ăn";
                break;
            case 1:
                title = "Đồ uống";
                break;
        }

        return title;
    }
}
