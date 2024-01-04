package com.example.mucsicapp.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Activity.PlayNhacActivity;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class PlayNhacAdaptet extends RecyclerView.Adapter<PlayNhacAdaptet.ViewHolder> {
    Context context;
    ArrayList<Baihat> mangbaihatPlay = new ArrayList<>();

    public PlayNhacAdaptet(PlayNhacActivity activity, ArrayList<Baihat> mangbaihat) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_baihat, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihatPlay.get(position);
        holder.tvPlayTencasi.setText(baihat.getCasi());
        holder.tvPlayindex.setText(position + 1 + "");
        holder.tvPlayTenbaihat.setText(baihat.getTenBaihat());
    }

    @Override
    public int getItemCount() {
        return mangbaihatPlay.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPlayTencasi, tvPlayTenbaihat, tvPlayindex;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayindex = itemView.findViewById(R.id.tvPlayindex);
            tvPlayTenbaihat = itemView.findViewById(R.id.tvPlayTenbaihat);
            tvPlayTencasi = itemView.findViewById(R.id.tvPlayTencasi);
        }
    }
}
