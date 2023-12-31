package com.example.mucsicapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerPlayNhac extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentsarraylist = new ArrayList<>();
    public ViewPagerPlayNhac(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsarraylist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsarraylist.size();
    }
    public void AddFragment(Fragment fragment){
        fragmentsarraylist.add(fragment);
    }
}
