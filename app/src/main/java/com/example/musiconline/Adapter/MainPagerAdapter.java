package com.example.musiconline.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    ArrayList<String> titlearrayList=new ArrayList<>();
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
   public void addfragment(Fragment fragment,String title)
   {
       fragmentArrayList.add(fragment);
       titlearrayList.add(title);
   }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlearrayList.get(position);
    }
}
