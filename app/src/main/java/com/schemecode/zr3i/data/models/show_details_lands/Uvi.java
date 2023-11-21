
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Uvi {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("uvi")
    @Expose
    private Double uvi;
    @SerializedName("risk")
    @Expose
    private String risk;
    @SerializedName("color")
    @Expose
    private String color;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Uvi() {
    }

    /**
     * 
     * @param dt
     * @param color
     * @param uvi
     * @param risk
     */
    public Uvi(Integer dt, Double uvi, String risk, String color) {
        super();
        this.dt = dt;
        this.uvi = uvi;
        this.risk = risk;
        this.color = color;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Double getUvi() {
        return uvi;
    }

    public void setUvi(Double uvi) {
        this.uvi = uvi;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
