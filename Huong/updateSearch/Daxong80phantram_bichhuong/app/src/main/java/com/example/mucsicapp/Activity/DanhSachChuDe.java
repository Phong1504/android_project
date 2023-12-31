package com.example.mucsicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.mucsicapp.Adapter.DanhsachAllchude;
import com.example.mucsicapp.Model.ChuDe;
import com.example.mucsicapp.R;

import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDe extends AppCompatActivity {

    RecyclerView recyclerViewAllchude;
    androidx.appcompat.widget.Toolbar toolbarallchude;
    DanhsachAllchude danhsachAllchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        Init();
        Data();
    }

    private void Data() {

        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.getAllChude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {

                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhsachAllchudeAdapter =  new DanhsachAllchude(DanhSachChuDe.this, mangchude);
                recyclerViewAllchude.setLayoutManager(new GridLayoutManager(DanhSachChuDe.this, 1));
                recyclerViewAllchude.setAdapter(danhsachAllchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void Init() {
        recyclerViewAllchude=findViewById(R.id.recycleallChude);
        toolbarallchude = findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbarallchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarallchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}