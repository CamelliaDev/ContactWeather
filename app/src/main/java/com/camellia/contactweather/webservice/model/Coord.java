package com.camellia.contactweather.webservice.model;

import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    private float longitude;

    @SerializedName("lat")
    private float latitude;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

}
