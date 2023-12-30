package com.example.spotifire;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppLocalesMetadataHolderService;
import androidx.recyclerview.widget.RecyclerView;

import java.net.ResponseCache;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{

    Context context;
    //ArrayList<BaiHat> mangbaihat;
    public SearchBaiHatAdapter(Context context,ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mangbaihat = mangbaihat;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.txtTenbaihat.setText(baihat.getTenbaihat());
        holder.txtCasi.setText(baihat.getCasi);
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat,txtCasi;
        ImageView imgbaihat, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtCasi = itemView.findViewById(R.id.textviewsearchtencasi);
            imgbaihat=itemView.findViewById(R.id.imageveiwSearchbaihat);
            imgluotthich=itemView.findViewById(R.id.imageveiwSearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>()){
                        public void onResponse(Call<String> call, Response<String> response){
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
                                Toast.makeText(context,"Đã Thích", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(context, "Err", Toast.LENGTH_SHORT).show();
                            }
                        }
                        public void onFailure(Call<String> call,Throwable t){

                        }
                    });
                    imgluotthich.setEnabled(false);
                 }
            });
        }
    }

}
