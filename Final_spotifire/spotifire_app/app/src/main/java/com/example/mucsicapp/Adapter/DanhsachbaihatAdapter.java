package com.example.mucsicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Activity.PlayNhacActivity;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.R;

import java.util.ArrayList;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            imgluotthich = itemView.findViewById(R.id.img_iconluotthich);

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.Updateluotthich("1", mangbaihat.get(getPosition()).getIdbaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua != null && ketqua.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("ca khuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

