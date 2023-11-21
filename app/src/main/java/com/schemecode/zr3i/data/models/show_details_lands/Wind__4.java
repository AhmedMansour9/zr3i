
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Wind__4 {

    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("gust")
    @Expose
    private Double gust;
    @SerializedName("speed")
    @Expose
    private Double speed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wind__4() {
    }

    /**
     * 
     * @param deg
     * @param speed
     * @param gust
     */
    public Wind__4(Integer deg, Double gust, Double speed) {
        super();
        this.deg = deg;
        this.gust = gust;
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
