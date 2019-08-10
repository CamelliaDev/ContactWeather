package com.camellia.contactweather.webservice.model;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("type")
    private int type;

    @SerializedName("id")
    private int id;

    @SerializedName("message")
    private float message;

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private float sunrise;

    @SerializedName("sunset")
    private float sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getSunrise() {
        return sunrise;
    }

    public void setSunrise(float sunrise) {
        this.sunrise = sunrise;
    }

    public float getSunset() {
        return sunset;
    }

    public void setSunset(float sunset) {
        this.sunset = sunset;
    }
}
