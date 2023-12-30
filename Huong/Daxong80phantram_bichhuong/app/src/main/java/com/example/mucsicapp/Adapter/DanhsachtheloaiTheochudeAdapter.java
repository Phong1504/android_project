package com.example.mucsicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Activity.DanhsachBaihatActivity;
import com.example.mucsicapp.Model.TheLoai;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtheloaiTheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaiTheochudeAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> mangtheloai;

    public DanhsachtheloaiTheochudeAdapter(Context context, ArrayList<TheLoai> mangtheloai) {
        this.context = context;
        this.mangtheloai = mangtheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai_theo_chude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai= mangtheloai.get(position);
        Picasso.get().load(theLoai.getHinhTheLoai()).into(holder.imghinhnen);
        holder.tvtentheloai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mangtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView tvtentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imgTLtheoCD);
            tvtentheloai = itemView.findViewById(R.id.tvTltheoCD);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhsachBaihatActivity.class);
                    intent.putExtra("idtheloai",mangtheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
