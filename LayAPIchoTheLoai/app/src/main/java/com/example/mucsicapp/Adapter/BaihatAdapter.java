package com.example.mucsicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaihatAdapter extends RecyclerView.Adapter<BaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public BaihatAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //dung de gan du lieu vao
        Baihat baihat = baihatArrayList.get(position);
        holder.tvcasi.setText(baihat.getCasi());
        holder.tvten.setText(baihat.getTenBaihat());
        Picasso.get().load(baihat.getHinhBaihat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvten, tvcasi;
        ImageView imghinh, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvten = itemView.findViewById(R.id.textviewTenbaihat);

            tvcasi = itemView.findViewById(R.id.tvTencasi);

            imghinh = itemView.findViewById(R.id.imagebaihat);

            imgluotthich = itemView.findViewById(R.id.imagluotthich);


        }
    }
}
