package com.example.mucsicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.R;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_baihat, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.tvcasi.setText(baihat.getCasi());
        holder.tvindex.setText(position + 1 + "");
        holder.tvtenbaihat.setText(baihat.getTenBaihat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvindex, tvtenbaihat,tvcasi;
    ImageView imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcasi = itemView.findViewById(R.id.tv_tencasi);
            tvindex = itemView.findViewById(R.id.tv_danhsachindex);
            tvtenbaihat = itemView.findViewById(R.id.tv_tenbaihat);
            imgluotthich = itemView.findViewById(R.id.imagluotthich);
        }
    }


}
