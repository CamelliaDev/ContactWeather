package com.camellia.contactweather.webservice.model;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    private float speed;

    @SerializedName("deg")
    private float deg;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDeg() {
        return deg;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }
}
