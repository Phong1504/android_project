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
import androidx.viewpager.widget.PagerAdapter;

import com.example.mucsicapp.Activity.DanhsachBaihatActivity;
import com.example.mucsicapp.Model.QuangCao;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;
    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner){
        this.context = context;
        this.arrayListBanner = arrayListBanner;

    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imagebackroundbanner = view.findViewById(R.id.imageviewbackgroungbanner);
        ImageView imagebanner = view.findViewById(R.id.imageview_banner);
        TextView titlebanner = view.findViewById(R.id.Textviewtitlebannerbaihat);
        TextView textviewmota = view.findViewById(R.id.textviewmota);

        Picasso.get().load(arrayListBanner.get(position).getHinhanhQuangcao()).into(imagebackroundbanner);
        Picasso.get().load(arrayListBanner.get(position).getHinhBaiHat()).into(imagebanner);
        textviewmota.setText(arrayListBanner.get(position).getNoiDung());
        titlebanner.setText(arrayListBanner.get(position).getTenBaiHat());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Lolo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DanhsachBaihatActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
