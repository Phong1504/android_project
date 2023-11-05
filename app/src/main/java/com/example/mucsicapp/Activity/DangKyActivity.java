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

public class DangKyActivity extends AppCompatActivity {
    private EditText etTaiKhoan, etMatKhau;
    private Button btndangky;
    private FirebaseAuth fbaMusicApp;
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);
        fbaMusicApp = FirebaseAuth.getInstance();

        etTaiKhoan = findViewById(R.id.et_taikhoan);
        etMatKhau = findViewById(R.id.et_matkhau);
        btndangky = findViewById(R.id.btn_dang_ky);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }
    private void DangKy(){
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
        fbaMusicApp.createUserWithEmailAndPassword(sTaiKhoan, sMatKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công.", Toast.LENGTH_SHORT).show();
                    Intent iMain = new Intent(DangKyActivity.this, MainActivity.class);
                    startActivity(iMain);
                }else{
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản không thành công.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
