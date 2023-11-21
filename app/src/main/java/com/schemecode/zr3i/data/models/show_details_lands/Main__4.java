
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Main__4 {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("temp_kf")
    @Expose
    private Integer tempKf;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("sea_level")
    @Expose
    private Integer seaLevel;
    @SerializedName("feels_like")
    @Expose
    private Double feelsLike;
    @SerializedName("grnd_level")
    @Expose
    private Integer grndLevel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main__4() {
    }

    /**
     * 
     * @param tempMax
     * @param feelsLike
     * @param temp
     * @param seaLevel
     * @param humidity
     * @param tempKf
     * @param pressure
     * @param grndLevel
     * @param tempMin
     */
    public Main__4(Double temp, Integer tempKf, Integer humidity, Integer pressure, Double tempMax, Double tempMin, Integer seaLevel, Double feelsLike, Integer grndLevel) {
        super();
        this.temp = temp;
        this.tempKf = tempKf;
        this.humidity = humidity;
        this.pressure = pressure;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.seaLevel = seaLevel;
        this.feelsLike = feelsLike;
        this.grndLevel = grndLevel;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getTempKf() {
        return tempKf;
    }

    public void setTempKf(Integer tempKf) {
        this.tempKf = tempKf;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Integer seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Integer grndLevel) {
        this.grndLevel = grndLevel;
    }

}
