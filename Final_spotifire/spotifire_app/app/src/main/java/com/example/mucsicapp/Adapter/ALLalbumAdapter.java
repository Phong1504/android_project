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
import com.example.mucsicapp.Model.Album;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ALLalbumAdapter extends RecyclerView.Adapter<ALLalbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public ALLalbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album, parent, false);
        return new ViewHolder(view);
    }

    //tra gia tri cho moi item duoc gan vao
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Album album=albumArrayList.get(position);
        Picasso.get().load(album.getHinhAlbum()).into(holder.imgalllalbum);
        holder.textViewallalbum.setText(album.getTenAlbum());
    }
    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgalllalbum;
        TextView textViewallalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgalllalbum = itemView.findViewById(R.id.imgllAlbum);
            textViewallalbum  =itemView.findViewById(R.id.tvtenallAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachBaihatActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
