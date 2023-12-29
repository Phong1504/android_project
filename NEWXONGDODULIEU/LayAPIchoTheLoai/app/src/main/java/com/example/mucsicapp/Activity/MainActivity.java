package com.example.mucsicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ViewFlipper;

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
        mainViewPagerAdapter.addFragment(new tab_tim_kiem(), "Tìm Kiếm");
        mainViewPagerAdapter.addFragment(new tab_ca_nhan(), "Cá Nhân");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(2).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconcanhan);

    }
}