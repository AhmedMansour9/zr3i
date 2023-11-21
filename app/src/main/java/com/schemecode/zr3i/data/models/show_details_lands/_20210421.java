
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20210421 {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main__3 main;
    @SerializedName("wind")
    @Expose
    private Wind__3 wind;
    @SerializedName("clouds")
    @Expose
    private Clouds__3 clouds;
    @SerializedName("weather")
    @Expose
    private List<Weather__4> weather = null;
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
    public _20210421() {
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
    public _20210421(Integer dt, Main__3 main, Wind__3 wind, Clouds__3 clouds, List<Weather__4> weather, String date, String time) {
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

    public Main__3 getMain() {
        return main;
    }

    public void setMain(Main__3 main) {
        this.main = main;
    }

    public Wind__3 getWind() {
        return wind;
    }

    public void setWind(Wind__3 wind) {
        this.wind = wind;
    }

    public Clouds__3 getClouds() {
        return clouds;
    }

    public void setClouds(Clouds__3 clouds) {
        this.clouds = clouds;
    }

    public List<Weather__4> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__4> weather) {
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
