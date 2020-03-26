package com.example.musiconline.ManActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.musiconline.Adapter.MainPagerAdapter;
import com.example.musiconline.Fragment.Fragment_Tophit;
import com.example.musiconline.Fragment.Fragment_theloai;
import com.example.musiconline.Fragment.fragment_playlist;
import com.example.musiconline.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.mytablayout);
        viewPager=findViewById(R.id.viewpager123);
        init();
    }

    private void init() {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addfragment(new Fragment_Tophit(),"Top Hit");
        mainPagerAdapter.addfragment(new Fragment_theloai(),"Thể Loại");
        mainPagerAdapter.addfragment(new fragment_playlist(),"My Playlist");
        viewPager.setAdapter(mainPagerAdapter);
       tabLayout.setupWithViewPager(viewPager);
    }
}