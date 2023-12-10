package com.example.mucsicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mucsicapp.Adapter.DanhsachbaihatAdapter;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.Model.QuangCao;
import com.example.mucsicapp.Model.TheLoai;
import com.example.mucsicapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachBaihatActivity extends AppCompatActivity {

        CoordinatorLayout coordinatorLayout;
        CollapsingToolbarLayout collapsingToolbarLayout;
        Toolbar toolbar;
        RecyclerView recyclerView;
        ImageView imgdsbaihat;
        FloatingActionButton floatingActionButton;
        QuangCao quangCao;
        TheLoai theloai;
    ArrayList<Baihat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_baihat);
        DataIntent();
        anhxaView();
        inIT();

        //neu quangcao tontai va gettenbaihat khac null
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")){

            setValueInView(quangCao.getTenBaiHat(), quangCao.getHinhBaiHat());
            GetDsbaihatquangcao(quangCao.getIdQuangcao());
        }

        if (theloai != null && theloai.getTenTheLoai().equals("")){
            setValueInView(theloai.getTenTheLoai(),theloai.getHinhTheLoai());
            Getdatatheloai(theloai.getIDTheLoai());
        }

    }

    private void Getdatatheloai(String idtheloai){
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachtheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachBaihatActivity.this, mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachBaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDsbaihatquangcao(String idQuangcao) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDsbaihatquangcao(idQuangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachBaihatActivity.this, mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachBaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        if (collapsingToolbarLayout != null && imgdsbaihat != null) {
            collapsingToolbarLayout.setTitle(ten);
            try {
                URL url = new URL(hinh);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Picasso.get().load(hinh).into(imgdsbaihat);
        }
    }


    private void inIT() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void anhxaView() {
        coordinatorLayout = findViewById(R.id.coordinatordsbaihat);
        collapsingToolbarLayout = findViewById(R.id.colappsingtollbar);
        toolbar = findViewById(R.id.tollbardanhsach);
        recyclerView = findViewById(R.id.recycledanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbotton);
        imgdsbaihat = findViewById(R.id.imagedanhsachbaihat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent!=null){
            if (intent.hasExtra("banner")){
            quangCao = (QuangCao) intent.getSerializableExtra("banner");
            }

            if (intent.hasExtra("idtheloai")){
                theloai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
        }
    }
}