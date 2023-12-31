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
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mucsicapp.Adapter.DanhsachbaihatAdapter;
import com.example.mucsicapp.Model.Album;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.Model.QuangCao;
import com.example.mucsicapp.Model.TheLoai;
import com.example.mucsicapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

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
        Album album;
        ArrayList<Baihat> mangbaihat;
        DanhsachbaihatAdapter danhsachbaihatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_baihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxaView();
        inIT();

        //neu quangcao tontai va gettenbaihat khac null
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(), quangCao.getHinhanhQuangcao());
            GetDsbaihatquangcao(quangCao.getIdQuangcao());
        }


        if (theloai != null && !theloai.getTenTheLoai().equals("")){
            setValueInView(theloai.getTenTheLoai(),theloai.getHinhTheLoai());
            Getdatatheloai(theloai.getIDTheLoai());
        }

        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetAlbumListBaihat(album.getIdalbum());
        }

    }

    private void GetAlbumListBaihat(String idalbum) {
        DataService dataService= APIService.getService();
        Call<List<Baihat>> callback = dataService.GetAlbumListBaihat(idalbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachBaihatActivity.this, mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachBaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
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
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
            }
        });
    }

    private void GetDsbaihatquangcao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDsbaihatquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachBaihatActivity.this, mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachBaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
                //Log.e("GetDsbaihatquangcao", "Request failed: " + t.getMessage());
            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        if (collapsingToolbarLayout != null && imgdsbaihat != null) {
            collapsingToolbarLayout.setTitle(ten);

            // Tạo một RequestCreator bằng cách sử dụng Picasso
            RequestCreator requestCreator = Picasso.get().load(hinh);
            // Xử lý lỗi khi tải hình ảnh
            requestCreator.error(R.drawable.bg2).into(imgdsbaihat);
            try {
                // Lấy một URL từ String
                URL url = new URL(hinh);
                // Tạo luồng để tải hình ảnh từ URL và set vào backdrop của collapsing toolbar
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            final BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    collapsingToolbarLayout.setBackground(bitmapDrawable);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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
        floatingActionButton.setEnabled(false);
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
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangCao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
        }

        if (intent != null){
                if (intent.hasExtra("idtheloai")){
                    theloai = (TheLoai) intent.getSerializableExtra("idtheloai");
                }
            }

        if (intent != null){
                if (intent.hasExtra("album")){
                    album = (Album) intent.getSerializableExtra("album");
                }
            }


        }

        private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachBaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });

    }
    }