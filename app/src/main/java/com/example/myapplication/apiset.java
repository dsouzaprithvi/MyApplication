package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset {

    @GET("api.php")
    Call<List<responsemodel>> get();

    @FormUrlEncoded
    @POST("fetch_sms_of_device_id.php")
        Call<List<responsemodel>>getdata(
                @Field("device_id") String device_id
    );

    @FormUrlEncoded
    @POST("insert.php")
    Call<responsemodel>getmessage(
            @Field("device_id") String device_id,
            @Field("sms") String sms
    );

}
