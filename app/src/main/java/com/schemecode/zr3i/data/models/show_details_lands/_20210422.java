
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20210422 {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main__4 main;
    @SerializedName("wind")
    @Expose
    private Wind__4 wind;
    @SerializedName("clouds")
    @Expose
    private Clouds__4 clouds;
    @SerializedName("weather")
    @Expose
    private List<Weather__5> weather = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _20210422() {
    }

    /**
     * 
     * @param dt
     * @param date
     * @param weather
     * @param main
     * @param clouds
     * @param time
     * @param wind
     */
    public _20210422(Integer dt, Main__4 main, Wind__4 wind, Clouds__4 clouds, List<Weather__5> weather, String date, String time) {
        super();
        this.dt = dt;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.weather = weather;
        this.date = date;
        this.time = time;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main__4 getMain() {
        return main;
    }

    public void setMain(Main__4 main) {
        this.main = main;
    }

    public Wind__4 getWind() {
        return wind;
    }

    public void setWind(Wind__4 wind) {
        this.wind = wind;
    }

    public Clouds__4 getClouds() {
        return clouds;
    }

    public void setClouds(Clouds__4 clouds) {
        this.clouds = clouds;
    }

    public List<Weather__5> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__5> weather) {
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

    public void setTime(String time) {
        this.time = time;
    }

}
