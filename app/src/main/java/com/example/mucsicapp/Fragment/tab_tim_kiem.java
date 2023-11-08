package com.example.mucsicapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mucsicapp.R;

import org.jetbrains.annotations.Nullable;

public class tab_tim_kiem extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab_tim_kiem, container, false);
        return  view;
    }
}