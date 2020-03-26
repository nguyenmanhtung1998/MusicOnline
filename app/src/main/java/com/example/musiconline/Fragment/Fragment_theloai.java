package com.example.musiconline.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musiconline.Adapter.Theloai_Adapter;
import com.example.musiconline.Model.Playlist;
import com.example.musiconline.R;
import com.example.musiconline.Service.APIService;
import com.example.musiconline.Service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_theloai extends Fragment {
    View view;
    RecyclerView recyclerViewtheloai;
    ArrayList<Playlist> playlistArrayList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_theloai,container,false);
        recyclerViewtheloai=view.findViewById(R.id.Recycleviewtheloai);
        getdatatheloai();

        return view;
    }

    private void getdatatheloai() {
        Service service= APIService.getService();
        Call<List<Playlist>> call=service.Getplaylist();
        call.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlistArrayList= (ArrayList<Playlist>) response.body();
                Theloai_Adapter theloai_adapter=new Theloai_Adapter(getActivity(),playlistArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewtheloai.setLayoutManager(linearLayoutManager);
                recyclerViewtheloai.setAdapter(theloai_adapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
