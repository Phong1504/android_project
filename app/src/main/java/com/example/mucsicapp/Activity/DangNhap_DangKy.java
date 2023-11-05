package com.example.mucsicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mucsicapp.R;

public class DangNhap_DangKy extends AppCompatActivity {
    private Button btnDangKy, btnDangNhap;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_dau_tien);

        btnDangNhap = findViewById(R.id.btn_dangnhap);
        btnDangKy = findViewById(R.id.btn_dangky);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhapVaoApp();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKyTaiKhoan();
            }
        });
    }

    private void DangNhapVaoApp(){
        Intent iDangNhap = new Intent(DangNhap_DangKy.this, DangNhapActivity.class);
        startActivity(iDangNhap);
    }
    private void DangKyTaiKhoan(){
        Intent iDangKy = new Intent(DangNhap_DangKy.this, DangKyActivity.class);
        startActivity(iDangKy);
    }
}
