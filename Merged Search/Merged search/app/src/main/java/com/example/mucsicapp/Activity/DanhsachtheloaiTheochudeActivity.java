package com.example.mucsicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mucsicapp.Adapter.DanhsachtheloaiTheochudeAdapter;
import com.example.mucsicapp.Model.ChuDe;
import com.example.mucsicapp.Model.TheLoai;
import com.example.mucsicapp.R;

import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaiTheochudeActivity extends AppCompatActivity {
    ChuDe chude;
    RecyclerView recyclerViewtheloaiTheochude;
    Toolbar toolbarTltheoCD;
    DanhsachtheloaiTheochudeAdapter danhsachtheloaiTheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloai_theochude);
        GetIntent();
        IniIt();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetTheloaiTheoChude(chude.getIDchude());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                danhsachtheloaiTheochudeAdapter = new DanhsachtheloaiTheochudeAdapter(DanhsachtheloaiTheochudeActivity.this, mangtheloai);
                recyclerViewtheloaiTheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaiTheochudeActivity.this, 1));
                recyclerViewtheloaiTheochude.setAdapter(danhsachtheloaiTheochudeAdapter);

              }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void IniIt() {
        recyclerViewtheloaiTheochude = findViewById(R.id.recyclertheloaiTHEOchude);
        toolbarTltheoCD = findViewById(R.id.toolbarTLtheoCD);
        setSupportActionBar(toolbarTltheoCD);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chude.getTenChude());
        toolbarTltheoCD.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")){
            chude = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}