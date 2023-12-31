package com.example.mucsicapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mucsicapp.Activity.DangNhap_DangKy;
import com.example.mucsicapp.Activity.MainActivity;
import com.example.mucsicapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import API.APIRetrofit;
import API.DataService;


public class tab_ca_nhan extends Fragment {
    //private Button btndangxuat;
    private FirebaseAuth fbaMusicApp;
    //MainActivity ma;
    //View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fbaMusicApp = FirebaseAuth.getInstance();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab_ca_nhan, container, false);

        // Cap nhat email nguoi dung da su dung de dang ky tai khoan len goc trai ung dung
        TextView emailnguoidung = view.findViewById(R.id.email_nguoi_dung);
        String emailNguoiDung = fbaMusicApp.getCurrentUser().getEmail();
        emailnguoidung.setText(emailNguoiDung);

        // Cap nhat id nguoi dung len goc trai ung dung, duoi email nguoi dung
        TextView id = view.findViewById(R.id.id);
        String iD = fbaMusicApp.getCurrentUser().getUid();
        id.setText(iD);


        Button btndangxuat = view.findViewById(R.id.btn_dang_xuat);
        btndangxuat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DangXuat();
            }

        });
        return view;
    }
    private void DangXuat()
    {
        fbaMusicApp.signOut();
        Intent iFirst = new Intent(getActivity(), DangNhap_DangKy.class);
        // Xoa bo toan bo cac Activity khac nam sau Activity DangNhap_DangKy
        iFirst.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(iFirst);
        getActivity().finish();
        System.exit(0);
    }
}
