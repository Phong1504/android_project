package com.example.mucsicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Adapter.BaihatAdapter;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.Model.QuangCao;
import com.example.mucsicapp.R;

import java.util.ArrayList;
import java.util.List;

import API.APIService;
import API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Baihat extends Fragment {
    View view;
    RecyclerView recyclerViewbaihathot;
    BaihatAdapter baihatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat,container, false);
        recyclerViewbaihathot = view.findViewById(R.id.recyclebaihat);
        GetData();
        return view;

    }

    private void GetData() {
        DataService dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaihatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baihatAdapter = new BaihatAdapter(getActivity(),baihatArrayList);
                //quy dinh viewgroup sap xep nhu the nao
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewbaihathot.setAdapter(baihatAdapter);

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
            }
        });
    }
}