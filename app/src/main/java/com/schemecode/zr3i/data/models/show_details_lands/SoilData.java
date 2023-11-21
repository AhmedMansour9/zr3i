
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SoilData {
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
    public SoilData() {
    }

    /**
     *
     * @param dt
     * @param t10
     * @param moisture
     * @param t0
     */
    public SoilData(Integer dt, Double t0, Double t10, Double moisture) {
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

//    @SerializedName("indicators")
//    @Expose
//    private Indicators indicators;
//    @SerializedName("info")
//    @Expose
//    private Info__2 info;
//    @SerializedName("uvi")
//    @Expose
//    private Uvi uvi;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public SoilData() {
//    }
//
//    /**
//     *
//     * @param uvi
//     * @param indicators
//     * @param info
//     */
//    public SoilData(Indicators indicators, Info__2 info, Uvi uvi) {
//        super();
//        this.indicators = indicators;
//        this.info = info;
//        this.uvi = uvi;
//    }
//
//    public Indicators getIndicators() {
//        return indicators;
//    }
//
//    public void setIndicators(Indicators indicators) {
//        this.indicators = indicators;
//    }
//
//    public Info__2 getInfo() {
//        return info;
//    }
//
//    public void setInfo(Info__2 info) {
//        this.info = info;
//    }
//
//    public Uvi getUvi() {
//        return uvi;
//    }
//
//    public void setUvi(Uvi uvi) {
//        this.uvi = uvi;
//    }

}
