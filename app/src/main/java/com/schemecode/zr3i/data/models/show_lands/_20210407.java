
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _20210407 {

    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("max_temp")
    @Expose
    private Double maxTemp;
    @SerializedName("min_temp")
    @Expose
    private Double minTemp;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("wind_deg")
    @Expose
    private Integer windDeg;
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    @SerializedName("cloud_cover")
    @Expose
    private Integer cloudCover;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _20210407() {
    }

    /**
     * 
     * @param windDeg
     * @param station
     * @param maxTemp
     * @param cloudCover
     * @param humidity
     * @param pressure
     * @param windSpeed
     * @param minTemp
     */
    public _20210407(String station, Integer humidity, Double maxTemp, Double minTemp, Integer pressure, Integer windDeg, Double windSpeed, Integer cloudCover) {
        super();
        this.station = station;
        this.humidity = humidity;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.pressure = pressure;
        this.windDeg = windDeg;
        this.windSpeed = windSpeed;
        this.cloudCover = cloudCover;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Integer cloudCover) {
        this.cloudCover = cloudCover;
    }

}
