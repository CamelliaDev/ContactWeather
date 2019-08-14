package com.camellia.contactweather.webservice;

import com.camellia.contactweather.webservice.model.DataModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String APP_ID = "ddc20b0608e119c78cd5cf5967ae8e5f";

    private static ApiManager sInstance = null;

    private APIInterface api;

    public static ApiManager getInstance() {
        if (sInstance == null) {
            sInstance = new ApiManager();
        }
        return sInstance;
    }

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APIInterface.class);
    }

    public Call<DataModel> getCurrentWeather(double lat, double lon) {
        return api.getCurrentWeather(lat, lon, APP_ID);
    }

}
