
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
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
    @SerializedName("feels_like")
    @Expose
    private Double feelsLike;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param tempMax
     * @param feelsLike
     * @param temp
     * @param humidity
     * @param pressure
     * @param tempMin
     */
    public Main(Double temp, Integer humidity, Integer pressure, Double tempMax, Double tempMin, Double feelsLike) {
        super();
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.feelsLike = feelsLike;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

}
