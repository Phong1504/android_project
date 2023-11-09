package com.example.a.Service;

public class APIService {
    private static String base_url ="https://baophong.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofit.getClient(base_url).create(DataService.class);

    }

}
