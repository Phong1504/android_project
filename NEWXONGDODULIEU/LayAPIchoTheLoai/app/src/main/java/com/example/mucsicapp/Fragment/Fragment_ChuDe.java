package com.example.mucsicapp.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mucsicapp.Activity.DanhsachBaihatActivity;
import com.example.mucsicapp.Model.ChuDe;
import com.example.mucsicapp.Model.TheLoai;
import com.example.mucsicapp.Model.Theloaitrongngay;
import com.example.mucsicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe extends Fragment {
        View view;
        HorizontalScrollView horizontalScrollView;
        TextView tvxemthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment__chu_de, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        tvxemthem = view.findViewById(R.id.tvxemthemchudetheloai);

        GetData();
        return view ;

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<Theloaitrongngay> callback = dataService.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                Theloaitrongngay theloaitrongngay = (Theloaitrongngay) response.body();
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theloaitrongngay.getTheLoai());


                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(400,400);
                layout.setMargins(10,20,10,30);


                for (int i = 0 ; i< (chuDeArrayList.size()) ; i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(15);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if ((chuDeArrayList.get(i).getHinhChude() != null)){
                        Picasso.get().load(chuDeArrayList.get(i).getHinhChude()).into(imageView);
                        }
                        cardView.setLayoutParams(layout);
                        cardView.addView(imageView);
                        linearLayout.addView(cardView);
                    }
                for (int j = 0 ; j< (theLoaiArrayList.size()) ; j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(15);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if ((theLoaiArrayList.get(j).getHinhTheLoai() != null)){
                        Picasso.get().load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachBaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });


                }

                horizontalScrollView.addView(linearLayout);
                }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {
            }
        });
    }
}