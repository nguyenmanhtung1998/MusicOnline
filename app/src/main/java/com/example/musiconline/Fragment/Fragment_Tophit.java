package com.example.musiconline.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musiconline.Adapter.Top_hit_Adapter;
import com.example.musiconline.Model.Baihat;
import com.example.musiconline.R;
import com.example.musiconline.Service.APIService;
import com.example.musiconline.Service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tophit  extends Fragment {
    View view;
    RecyclerView recyclerView;
    ArrayList<Baihat> baihatArrayList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_top_hit,container,false);
        recyclerView=view.findViewById(R.id.Recycleviewtophit);

        getdata();
        return view;
    }

    private void getdata() {
        Service service= APIService.getService();
        Call<List<Baihat>> call=service.GetdataTophit();
        call.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                   baihatArrayList= (ArrayList<Baihat>) response.body();
                Top_hit_Adapter top_hit_adapter=new Top_hit_Adapter(getActivity(),baihatArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.setAdapter(top_hit_adapter);
            }
            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
