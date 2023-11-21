package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherExpectationObject {

    boolean isExpandable = false;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main__2 main;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("wind")
    @Expose
    private Wind__2 wind;
    @SerializedName("clouds")
    @Expose
    private Clouds__2 clouds;
    @SerializedName("weather")
    @Expose
    private List<Weather__3> weather = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main__2 getMain() {
        return main;
    }

    public void setMain(Main__2 main) {
        this.main = main;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Wind__2 getWind() {
        return wind;
    }

    public void setWind(Wind__2 wind) {
        this.wind = wind;
    }

    public Clouds__2 getClouds() {
        return clouds;
    }

    public void setClouds(Clouds__2 clouds) {
        this.clouds = clouds;
    }

    public List<Weather__3> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__3> weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
