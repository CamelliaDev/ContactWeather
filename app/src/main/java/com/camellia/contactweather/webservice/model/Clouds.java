package com.camellia.contactweather.webservice.model;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("clouds")
    private int clouds;

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

}
