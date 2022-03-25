package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {

    private static final String url = "http://192.168.1.5/riyadhgreen/";
    private static apicontroller clientobject;
    private static Retrofit retrofit;


    apicontroller(){


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized apicontroller getInstance(){

        if (clientobject == null){
            clientobject = new apicontroller();
        }

        return clientobject;
    }

    apiset getapi(){
        return retrofit.create(apiset.class);
    }


}
