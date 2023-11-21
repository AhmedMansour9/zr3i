
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Info__1 {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cod")
    @Expose
    private Integer cod;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("weather")
    @Expose
    private List<Weather__1> weather = null;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Info__1() {
    }

    /**
     * 
     * @param visibility
     * @param timezone
     * @param main
     * @param clouds
     * @param sys
     * @param dt
     * @param coord
     * @param name
     * @param weather
     * @param cod
     * @param id
     * @param base
     * @param wind
     */
    public Info__1(Integer dt, Integer id, Integer cod, Sys sys, String base, Main main, String name, Wind wind, Coord coord, Clouds clouds, List<Weather__1> weather, Integer timezone, Integer visibility) {
        super();
        this.dt = dt;
        this.id = id;
        this.cod = cod;
        this.sys = sys;
        this.base = base;
        this.main = main;
        this.name = name;
        this.wind = wind;
        this.coord = coord;
        this.clouds = clouds;
        this.weather = weather;
        this.timezone = timezone;
        this.visibility = visibility;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public List<Weather__1> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather__1> weather) {
        this.weather = weather;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

}
