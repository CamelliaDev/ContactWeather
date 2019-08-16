package com.camellia.contactweather.main;

import com.camellia.contactweather.webservice.model.DataModel;

import java.util.HashMap;

public class WeatherCache {

    private static WeatherCache instance;
    private HashMap<String, DataModel> hashMap = new HashMap<>();

    private WeatherCache() {
    }

    public static WeatherCache getInstance() {
        if (instance == null) {
            instance = new WeatherCache();
        }
        return instance;
    }

    public DataModel getCacheData(String cityName) {
        return hashMap.get(cityName);
    }

    public void putCacheData(String cityName, DataModel data) {
        hashMap.put(cityName, data);
    }

    public void clearCache() {
        hashMap.clear();
    }
}
