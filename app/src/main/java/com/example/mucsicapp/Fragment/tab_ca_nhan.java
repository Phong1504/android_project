package com.example.mucsicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mucsicapp.Activity.DangNhap_DangKy;
import com.example.mucsicapp.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.Nullable;


public class tab_ca_nhan extends Fragment {
    //private Button btndangxuat;
    private FirebaseAuth fbaMusicApp;
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
        startActivity(iFirst);
        getActivity().finish();
    }
}