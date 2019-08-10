package com.camellia.contactweather.webservice.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataModel {

    @SerializedName("coord")
    public Coord coord;

    @SerializedName("weather")
    public ArrayList<Weather> weather = new ArrayList<>();

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    public Main main;

    @SerializedName("visibility")
    private int visibility;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("clouds")
    public Clouds clouds;

    @SerializedName("dt")
    private float dt;

    @SerializedName("sys")
    public Sys sys;

    @SerializedName("timezone")
    private float timezone;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("code")
    private int code;


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public float getDt() {
        return dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public float getTimezone() {
        return timezone;
    }

    public void setTimezone(float timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
