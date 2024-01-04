package com.example.mucsicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mucsicapp.Activity.PlayNhacActivity;
import com.example.mucsicapp.Adapter.PlayNhacAdaptet;
import com.example.mucsicapp.R;
import androidx.fragment.app.Fragment;

public class Fragment_Play_DS_baohat extends Fragment {

    View view;
    RecyclerView recyclerViewPlaynhac;
    PlayNhacAdaptet playNhacAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_da_baihat, container, false);
        recyclerViewPlaynhac = view.findViewById(R.id.recyclePlaybaihat);

        if (PlayNhacActivity.mangbaihat.size() > 0) {
            playNhacAdapter = new PlayNhacAdaptet((PlayNhacActivity) getActivity(), PlayNhacActivity.mangbaihat);
            recyclerViewPlaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlaynhac.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
