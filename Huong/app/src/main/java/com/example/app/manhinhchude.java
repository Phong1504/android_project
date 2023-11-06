package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class manhinhchude extends AppCompatActivity {

    RecyclerView recyclerdschude;
    Toolbar toolbarchude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchude);

        init();

    }
    private void init(){
        recyclerdschude = findViewById(R.id.recyclerchude);
        toolbarchude = findViewById(R.id.toolbarmanhinhchude);
        setSupportActionBar(toolbarchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}