package com.example.mucsicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Activity.PlayNhacActivity;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihatAdapter extends RecyclerView.Adapter<BaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList = new ArrayList<>();

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
        return baihatArrayList != null ? baihatArrayList.size() : 0;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("ca khuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.Updateluotthich("1", baihatArrayList.get(getPosition()).getIdbaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Bỏ Thích", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("BaihatAdapter", "API Call Failed: " + t.getMessage());
                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
