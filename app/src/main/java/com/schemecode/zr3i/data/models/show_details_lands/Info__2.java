
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Info__2 {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("t0")
    @Expose
    private Double t0;
    @SerializedName("t10")
    @Expose
    private Double t10;
    @SerializedName("moisture")
    @Expose
    private Double moisture;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Info__2() {
    }

    /**
     * 
     * @param dt
     * @param t10
     * @param moisture
     * @param t0
     */
    public Info__2(Integer dt, Double t0, Double t10, Double moisture) {
        super();
        this.dt = dt;
        this.t0 = t0;
        this.t10 = t10;
        this.moisture = moisture;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Double getT0() {
        return t0;
    }

    public void setT0(Double t0) {
        this.t0 = t0;
    }

    public Double getT10() {
        return t10;
    }

    public void setT10(Double t10) {
        this.t10 = t10;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }

}
