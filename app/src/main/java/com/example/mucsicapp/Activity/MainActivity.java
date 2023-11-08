package com.example.mucsicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mucsicapp.Adapter.MainViewPagerAdapter;
import com.example.mucsicapp.Fragment.tab_ca_nhan;
import com.example.mucsicapp.Fragment.tab_tim_kiem;
import com.example.mucsicapp.Fragment.tab_trang_chu;
import com.example.mucsicapp.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    tab_trang_chu trangchuFragment = new tab_trang_chu();
    tab_tim_kiem timkiemFragment = new tab_tim_kiem();
    tab_ca_nhan canhanFragment = new tab_ca_nhan();

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Thao tac vuot
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new tab_trang_chu(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(new tab_tim_kiem(), "Tìm Kiếm");
        mainViewPagerAdapter.addFragment(new tab_ca_nhan(), "Cá Nhân");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconcanhan);

    }
}