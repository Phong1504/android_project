package com.example.mucsicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mucsicapp.Fragment.tab_ca_nhan;
import com.example.mucsicapp.Fragment.tab_tim_kiem;
import com.example.mucsicapp.Fragment.tab_trang_chu;
import com.example.mucsicapp.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    tab_trang_chu trangchuFragment = new tab_trang_chu();
    tab_tim_kiem timkiemFragment = new tab_tim_kiem();
    tab_ca_nhan canhanFragment = new tab_ca_nhan();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, trangchuFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.trang_chu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, trangchuFragment).commit();
                    Intent intent = new Intent(MainActivity.this, trangchuFragment.getClass());
                    return true;
                }else if(item.getItemId() == R.id.tim_kiem) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, timkiemFragment).commit();
                    Intent intent = new Intent(MainActivity.this, timkiemFragment.getClass());
                    return true;
                }else if(item.getItemId() == R.id.ca_nhan) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, canhanFragment).commit();
                    Intent intent = new Intent(MainActivity.this, canhanFragment.getClass());
                    return true;
                }else
                    return false;
            };
        });
    }
}