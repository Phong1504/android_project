package com.example.mucsicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mucsicapp.Adapter.BannerAdapter;
import com.example.mucsicapp.Model.QuangCao;
import com.example.mucsicapp.R;

import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment__banner, container, false);
        GetData();
        anhxa();
        return view;
    }

    private void anhxa() {
    viewPager = view.findViewById(R.id.viewpager);
    circleIndicator = view.findViewById(R.id.cirlleindicattor);


    }

    private void GetData() {
        DataService dataservice = APIService.getService();
        Call<List<QuangCao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banner = (ArrayList<QuangCao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(), banner);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                // Move the handler and runnable setup here
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        PagerAdapter adapter = viewPager.getAdapter();
                        if (adapter != null) {
                            currentItem++;
                        }

                        if (currentItem >= viewPager.getAdapter().getCount()) {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
                // Handle failure
            }
        });
    }

}