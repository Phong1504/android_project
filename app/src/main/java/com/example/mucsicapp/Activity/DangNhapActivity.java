package com.example.mucsicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mucsicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {
    private EditText etTaiKhoan, etMatKhau;
    private Button btndangnhap;
    private FirebaseAuth fbaMusicApp;
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        fbaMusicApp = FirebaseAuth.getInstance();

        etTaiKhoan = findViewById(R.id.et_taikhoan);
        etMatKhau = findViewById(R.id.et_matkhau);
        btndangnhap = findViewById(R.id.btn_dang_ky);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });
    }
    private void DangNhap(){
        String sTaiKhoan, sMatKhau;
        sTaiKhoan = etTaiKhoan.getText().toString();
        sMatKhau = etMatKhau.getText().toString();
        if(TextUtils.isEmpty(sTaiKhoan)){
            Toast.makeText(this, "Vui lòng nhập email.", Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(sMatKhau)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu.", Toast.LENGTH_SHORT);
            return;
        }
        fbaMusicApp.signInWithEmailAndPassword(sTaiKhoan, sMatKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                    Intent iMain = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(iMain);
                }else{
                    Toast.makeText(getApplicationContext(), "Đăng nhập không thành công.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}