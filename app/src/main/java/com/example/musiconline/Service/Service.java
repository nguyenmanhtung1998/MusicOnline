package com.example.musiconline.Service;

import com.example.musiconline.Model.Baihat;
import com.example.musiconline.Model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("tophit.php")
    Call<List<Baihat>> GetdataTophit();
    @GET("playlistcuren.php")
    Call<List<Playlist>> Getplaylist();

}
