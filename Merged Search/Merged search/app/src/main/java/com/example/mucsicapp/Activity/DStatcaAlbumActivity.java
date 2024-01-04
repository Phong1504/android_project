package com.example.mucsicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mucsicapp.Adapter.ALLalbumAdapter;
import com.example.mucsicapp.Model.Album;
import com.example.mucsicapp.Model.ChuDe;
import com.example.mucsicapp.R;

import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DStatcaAlbumActivity extends AppCompatActivity {


    RecyclerView recyclerViewAllalbum;
    Toolbar toolbarAllalbum;
    ALLalbumAdapter alLalbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstatca_album);
        iniT();
        GetData();
    }

    private void iniT() {
        recyclerViewAllalbum = findViewById(R.id.recyclerDSAllalbum);
        toolbarAllalbum = findViewById(R.id.toolbarTatcaalbum);
        setSupportActionBar(toolbarAllalbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarAllalbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetALlAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                alLalbumAdapter= new ALLalbumAdapter(DStatcaAlbumActivity.this, mangalbum);
                recyclerViewAllalbum.setLayoutManager(new GridLayoutManager(DStatcaAlbumActivity.this, 2));
                recyclerViewAllalbum.setAdapter(alLalbumAdapter);
            }
            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
            }
        });

    }
}