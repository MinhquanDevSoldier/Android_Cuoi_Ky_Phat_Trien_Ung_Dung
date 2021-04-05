package com.example.appqlquanoc.layout_activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appqlquanoc.DatabaseQuanOc;
import com.example.appqlquanoc.R;
import com.example.appqlquanoc.adapter.ViewPagerSelectFoodAdapter;
import com.example.appqlquanoc.doituong.SanPham;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class activity_select_food_layout extends AppCompatActivity {
    DatabaseQuanOc databaseQuanOc;
    TabLayout tabFelectFood;
    ViewPager viewPagerSelectFood;
    ArrayList<SanPham> arrayListsp = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food_layout);
        databaseQuanOc =new DatabaseQuanOc(getApplicationContext());
        arrayListsp.addAll(databaseQuanOc.layDuLieuSanPham());

        tabFelectFood = (TabLayout) findViewById(R.id.tab_select_food);
        viewPagerSelectFood = (ViewPager) findViewById(R.id.viewpager_select_food);

        ViewPagerSelectFoodAdapter viewPagerSelectFoodAdapter = new ViewPagerSelectFoodAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,arrayListsp);
        viewPagerSelectFood.setAdapter(viewPagerSelectFoodAdapter);
        tabFelectFood.setupWithViewPager(viewPagerSelectFood);
    }
}