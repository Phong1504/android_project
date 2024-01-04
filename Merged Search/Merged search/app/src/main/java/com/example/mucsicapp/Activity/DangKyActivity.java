package com.example.mucsicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mucsicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangKyActivity extends AppCompatActivity {
    private EditText etTaiKhoan, etMatKhau;
    private Button btndangky, btnverify;
    private FirebaseAuth fbaMusicApp;
    private CardView card1, card2, card3, card4;
    private boolean itNhat8 = false, coKyTuHoa = false, coKyTuThuong = false, coSo = false;
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);
        fbaMusicApp = FirebaseAuth.getInstance();

        //Ánh xạ
        etTaiKhoan = findViewById(R.id.et_taikhoan);
        etMatKhau = findViewById(R.id.et_matkhau);
        btnverify = findViewById(R.id.btn_verify);
        card1 = findViewById(R.id.cv1);
        card2 = findViewById(R.id.cv2);
        card3 = findViewById(R.id.cv3);
        card4 = findViewById(R.id.cv4);

        //Xử lý nút Verify
        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });

    }

    //Định nghĩa hàm xử lý nút Verify
    private void DangKy(){
        String sTaiKhoan, sMatKhau;
        sTaiKhoan = etTaiKhoan.getText().toString();
        sMatKhau = etMatKhau.getText().toString();
        //Kiểm tra rỗng cho các đối tượng "Email đăng nhập" và "Mật khẩu"
        if(TextUtils.isEmpty(sTaiKhoan)){
            Toast.makeText(this, "Vui lòng nhập email.", Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(sMatKhau)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu.", Toast.LENGTH_SHORT);
            return;
        }
        //Kiểm tra các điều kiện dành cho mật khẩu
        if(!kiemtraMatKhau(sMatKhau))
        {
            Toast.makeText(getApplicationContext(), "Mật khẩu không hợp lệ.", Toast.LENGTH_SHORT).show();
            return;
        }
        //Khởi tạo tài khoản trên Firebase Authentication
        fbaMusicApp.createUserWithEmailAndPassword(sTaiKhoan, sMatKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Xin hãy kiểm tra email và tiến hành xác thực tài khoản của bạn theo lời dẫn.", Toast.LENGTH_LONG).show();
                    //Gửi đường link xác thực đến email đã được cung cấp
                    FirebaseUser fbaMusicAppVerify = fbaMusicApp.getCurrentUser();
                    fbaMusicAppVerify.sendEmailVerification();
                    Intent iMain = new Intent(DangKyActivity.this, DangNhap_DangKy.class);
                    startActivity(iMain);
                }else{
                    Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Định nghĩa hàm kiểm tra các điều kiện dành cho Mật khẩu
    private boolean kiemtraMatKhau(String matkhau){
        //Chứa ít nhất 8 ký tự
        if(matkhau.length() >= 8)
        {
            itNhat8 = true;
            card1.setCardBackgroundColor(Color.parseColor("#BB98FA"));
        }else {
            itNhat8 = false;
            card1.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        //Chứa ít nhất 1 ký tự in hoa
        if(matkhau.matches("(.*[A-Z].*)"))
        {
            coKyTuHoa = true;
            card2.setCardBackgroundColor(Color.parseColor("#BB98FA"));
        }else {
            coKyTuHoa = false;
            card2.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        //Chứa ít nhất 1 ký tự in thường
        if(matkhau.matches("(.*[a-z].*)"))
        {
            coKyTuThuong = true;
            card3.setCardBackgroundColor(Color.parseColor("#BB98FA"));
        }else {
            coKyTuThuong = false;
            card3.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        //Chứa ít nhất 1 số
        if(matkhau.matches("(.*[0-9].*)"))
        {
            coSo = true;
            card4.setCardBackgroundColor(Color.parseColor("#BB98FA"));
        }else {
            coSo = false;
            card4.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        if(!itNhat8 || !coKyTuHoa || !coKyTuThuong || !coSo) {
            return false;
        }else {
            return true;
        }

    }

}
