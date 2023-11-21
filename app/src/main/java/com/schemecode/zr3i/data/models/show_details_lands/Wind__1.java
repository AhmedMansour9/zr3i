
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Wind__1 {

    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("speed")
    @Expose
    private Double speed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wind__1() {
    }

    /**
     * 
     * @param deg
     * @param speed
     */
    public Wind__1(Integer deg, Double speed) {
        super();
        this.deg = deg;
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
