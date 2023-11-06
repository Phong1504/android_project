package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class playlistst extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerdsplaylist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlistst);

        anhxa();
        init();

    }

    private void anhxa(){
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerdsplaylist = findViewById(R.id.recyclerplaylist);

    }
    @SuppressLint("RestrictedApi")
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mauxanh));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}