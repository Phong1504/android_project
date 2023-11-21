package com.example.mucsicapp.Fragment;

import android.os.Bundle;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.mucsicapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class tab_trang_chu extends Fragment {
    View view;
    ViewFlipper viewFlipper;

    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab_trang_chu, container, false);
        viewFlipper = view.findViewById(R.id.myViewFlipper);
        ActionViewFlipper();
        return  view;
    }

    private void ActionViewFlipper() {
        //Mang chua anh quang cao
        ArrayList<String> manganh = new ArrayList<>();
        manganh.add("https://avatar-ex-swe.nixcdn.com/slideshow/2023/11/09/a/3/9/b/1699540147522_org.jpg");
        manganh.add("https://avatar-ex-swe.nixcdn.com/slideshow/2023/11/09/a/3/9/b/1699540409363_org.jpg");
        manganh.add("https://avatar-ex-swe.nixcdn.com/slideshow/2023/11/09/a/3/9/b/1699540646260_org.jpg");
        manganh.add("https://avatar-ex-swe.nixcdn.com/slideshow/2023/11/08/b/f/b/a/1699440062881_org.jpg");

        for(int i = 0; i< manganh.size(); i++)
        {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());

            Picasso.get().load(manganh.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_right);
        //Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_left);

        viewFlipper.setInAnimation(animation_slide_in);
        //viewFlipper.setInAnimation(animation_slide_out);
    }
}