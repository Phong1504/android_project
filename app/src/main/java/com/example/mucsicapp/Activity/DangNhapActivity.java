package com.example.mucsicapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity {
    private EditText etTaiKhoan, etMatKhau;
    private Button btndangnhap, btnquenmtkhau;
    private CheckBox cbNho;
    SharedPreferences sharedPreferences;
    private FirebaseAuth fbaMusicApp;


    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.dang_nhap);
            fbaMusicApp = FirebaseAuth.getInstance();

            //Ánh xạ
            etTaiKhoan = findViewById(R.id.et_taikhoan);
            etMatKhau = findViewById(R.id.et_matkhau);
            btndangnhap = findViewById(R.id.btn_dang_nhap);
            btnquenmtkhau = findViewById(R.id.btn_quen_mat_khau);
            cbNho = findViewById(R.id.cb_nhotaikhoan);
            sharedPreferences = getSharedPreferences("QuanliTaiKhoan.txt", MODE_PRIVATE);
            etTaiKhoan.setText(sharedPreferences.getString("TaiKhoan", ""));
            etMatKhau.setText(sharedPreferences.getString("MatKhau", ""));
            cbNho.setChecked(sharedPreferences.getBoolean("NhoThongTinTaiKhoan", false));

            //Xử lý nút Đăng nhập
            btndangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DangNhap();
                }
            });
            //Xử lý bút Quên mật khẩu?
           btnquenmtkhau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {LayLaiMatKhau();}
            });

    }

    //Định nghĩa hàm xử lý nút Đăng nhập
    private void DangNhap(){
        String sTaiKhoan, sMatKhau;
        sTaiKhoan = etTaiKhoan.getText().toString();
        sMatKhau = etMatKhau.getText().toString();
        if (TextUtils.isEmpty(sTaiKhoan)) {
            Toast.makeText(this, "Vui lòng nhập email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sMatKhau)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }
        //Đăng nhập với Firebase Authentication
        fbaMusicApp.signInWithEmailAndPassword(sTaiKhoan, sMatKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser fbaMusicAppRegister = fbaMusicApp.getCurrentUser();
                    //Chỉ khi tài khoản đã được người dùng xác thực mới có thể truy cập ứng dụng
                    if(fbaMusicAppRegister.isEmailVerified()) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                        NhoTaiKhoan(sTaiKhoan, sMatKhau, cbNho.isChecked());
                        Intent iMain = new Intent(DangNhapActivity.this, MainActivity.class);
                        startActivity(iMain);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Vui lòng xác thực tài khoản của bạn.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập không thành công.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Trạng thái Lưu mật khẩu với Shared Preference
    public void NhoTaiKhoan(String taikhoan, String matkhau, boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else{
            editor.putString("TaiKhoan", taikhoan);
            editor.putString("MatKhau", matkhau);
            editor.putBoolean("NhoThongTinTaiKhoan", status);
        }
        editor.commit();
    }
    //Chuyển đến Activity Lấy lại mật khẩu
    public void LayLaiMatKhau(){
        Intent iLayLaiMatKhau = new Intent(DangNhapActivity.this, LayLaiMatKhauActivity.class);
        startActivity(iLayLaiMatKhau);
    }

}
