package com.example.mucsicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
import com.google.firebase.auth.FirebaseAuth;

public class LayLaiMatKhauActivity extends AppCompatActivity {
    private EditText email;
    private Button btnllmk, btnquaylai;
    private FirebaseAuth fbaMusicApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_lai_mat_khau);

        //Ánh xạ
        email = (EditText) findViewById(R.id.et_taikhoanlaylaimatkhau);
        btnllmk = (Button) findViewById(R.id.btn_lay_lai_mat_khau);
        btnquaylai = (Button) findViewById(R.id.btn_lay_lai_mat_khau_back);
        fbaMusicApp = FirebaseAuth.getInstance();
        //Xử lý bút Quay lại
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Xử lý bút Lấy lại mật khẩu
        btnllmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString().trim();
                if (TextUtils.isEmpty(sEmail)) {
                    Toast.makeText(getApplication(), "Vui lòng nhập email tài khoản của bạn.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Xử lí hành động gửi đường link khôi phục mật khẩu với Firebase Authentication
                fbaMusicApp.sendPasswordResetEmail(sEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LayLaiMatKhauActivity.this, "Thực thi thành công. Xin hãy kiểm tra hộp thư của bạn!", Toast.LENGTH_SHORT).show();
                            Intent iDangNhap = new Intent(LayLaiMatKhauActivity.this, DangNhapActivity.class);
                            startActivity(iDangNhap);
                        } else {
                            Toast.makeText(LayLaiMatKhauActivity.this, "Thực thi thất bại! Xin hãy thử lại sau.", Toast.LENGTH_SHORT).show();
                            Intent iDangNhap = new Intent(LayLaiMatKhauActivity.this, DangNhapActivity.class);
                            startActivity(iDangNhap);
                        }
                    }
                });
            }
        });
    }
}
