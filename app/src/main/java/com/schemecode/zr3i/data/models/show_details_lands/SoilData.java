
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SoilData {

    @SerializedName("indicators")
    @Expose
    private Indicators indicators;
    @SerializedName("info")
    @Expose
    private Info__2 info;
    @SerializedName("uvi")
    @Expose
    private Uvi uvi;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SoilData() {
    }

    /**
     * 
     * @param uvi
     * @param indicators
     * @param info
     */
    public SoilData(Indicators indicators, Info__2 info, Uvi uvi) {
        super();
        this.indicators = indicators;
        this.info = info;
        this.uvi = uvi;
    }

    public Indicators getIndicators() {
        return indicators;
    }

    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }

    public Info__2 getInfo() {
        return info;
    }

    public void setInfo(Info__2 info) {
        this.info = info;
    }

    public Uvi getUvi() {
        return uvi;
    }

    public void setUvi(Uvi uvi) {
        this.uvi = uvi;
    }

}
