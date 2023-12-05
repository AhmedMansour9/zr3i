package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.schemecode.zr3i.data.models.show_details_lands.Clouds__1;
import com.schemecode.zr3i.data.models.show_details_lands.Main__1;
import com.schemecode.zr3i.data.models.show_details_lands.Weather__2;
import com.schemecode.zr3i.data.models.show_details_lands.Wind__1;

public class ListDataByTime {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main__1 main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather__2> weather;
    @SerializedName("clouds")
    @Expose
    private Clouds__1 clouds;
    @SerializedName("wind")
    @Expose
    private Wind__1 wind;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("pop")
    @Expose
    private Integer pop;
//    @SerializedName("sys")
//    @Expose
//    private Sys__1 sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

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

    public java.util.List<Weather__2> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather__2> weather) {
        this.weather = weather;
    }

    public Clouds__1 getClouds() {
        return clouds;
    }

    public void setClouds(Clouds__1 clouds) {
        this.clouds = clouds;
    }

    public Wind__1 getWind() {
        return wind;
    }

    public void setWind(Wind__1 wind) {
        this.wind = wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

//    public Sys__1 getSys() {
//        return sys;
//    }
//
//    public void setSys(Sys__1 sys) {
//        this.sys = sys;
//    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
