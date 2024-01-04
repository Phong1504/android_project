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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> mangalbum;
    public AlbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent, false );
        return new ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangalbum.get(position);
        holder.tvtenAlbum.setText(album.getTenAlbum());
        holder.tvtencasialbum.setText(album.getTencasiAlbum());
        Picasso.get().load(album.getHinhAlbum()).into(holder.imghinhalbum);
    }
    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imghinhalbum;
            TextView tvtenAlbum, tvtencasialbum;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imghinhalbum = itemView.findViewById(R.id.imagealbum);
                tvtenAlbum= itemView.findViewById(R.id.textviewtenAlbum);
                tvtencasialbum= itemView.findViewById(R.id.textviewtencasiAlbum);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DanhsachBaihatActivity.class);
                        intent.putExtra("album", mangalbum.get(getPosition()));
                        context.startActivity(intent);
                    }
                });
            }

        public void tvtenAlbum(String tenAlbum) {
        }
    }
}
