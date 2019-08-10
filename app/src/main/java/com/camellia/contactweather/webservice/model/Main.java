package com.camellia.contactweather.webservice.model;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    private float temperature;

    @SerializedName("pressure")
    private float pressure;

    @SerializedName("humidity")
    private float humidity;

    @SerializedName("temp_min")
    private float temp_min;

    @SerializedName("temp_max")
    private float temp_max;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }
}
