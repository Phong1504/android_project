package com.example.mucsicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mucsicapp.Adapter.MainViewPagerAdapter;
import com.example.mucsicapp.Fragment.Fragment_Tim_Kiem;
import com.example.mucsicapp.Fragment.Fragment_addSong;
import com.example.mucsicapp.Fragment.tab_ca_nhan;
import com.example.mucsicapp.Fragment.tab_trang_chu;
import com.example.mucsicapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new tab_trang_chu(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm Kiếm");
        mainViewPagerAdapter.addFragment(new tab_ca_nhan(), "Cá Nhân");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(2).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconcanhan);

    }
}