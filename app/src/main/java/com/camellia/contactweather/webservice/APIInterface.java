package com.camellia.contactweather.webservice;

import com.camellia.contactweather.webservice.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("weather?")
    Call<DataModel> getCurrentWeather(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("APPID") String appId
    );
}

