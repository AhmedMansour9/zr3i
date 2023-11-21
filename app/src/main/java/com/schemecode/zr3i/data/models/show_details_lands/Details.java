
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Details {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main__1 main;
    @SerializedName("wind")
    @Expose
    private Wind__1 wind;
    @SerializedName("clouds")
    @Expose
    private Clouds__1 clouds;
    @SerializedName("weather")
    @Expose
    private List<Weather__2> weather = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Details() {
    }

    /**
     * 
     * @param dt
     * @param weather
     * @param main
     * @param clouds
     * @param wind
     */
    public Details(Integer dt, Main__1 main, Wind__1 wind, Clouds__1 clouds, List<Weather__2> weather) {
        super();
        this.dt = dt;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.weather = weather;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main__1 getMain() {
        return main;
    }

    public void setMain(Main__1 main) {
        this.main = main;
    }

    public Wind__1 getWind() {
        return wind;
    }

    public void setWind(Wind__1 wind) {
        this.wind = wind;
    }

    public Clouds__1 getClouds() {
        return clouds;
    }

    public void setClouds(Clouds__1 clouds) {
        this.clouds = clouds;
    }

    public List<Weather__2> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__2> weather) {
        this.weather = weather;
    }

}
