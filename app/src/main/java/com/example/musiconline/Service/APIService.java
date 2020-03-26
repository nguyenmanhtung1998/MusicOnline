package com.example.musiconline.Service;

public class APIService {
    private static String base_url="https://anacreontic-soups.000webhostapp.com/Server/";
    public  static Service getService(){
        return APIRetrofitClient.getClient(base_url).create(Service.class);
    }
}
