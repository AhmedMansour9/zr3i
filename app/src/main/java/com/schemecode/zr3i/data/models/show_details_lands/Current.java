
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Current {

    @SerializedName("info")
    @Expose
    private Info__1 info;
    @SerializedName("main")
    @Expose
    private Main__1 details;

    @SerializedName("wind")
    @Expose
    private Wind__1 wind;

    public Wind__1 getWind() {
        return wind;
    }

    public void setWind(Wind__1 wind) {
        this.wind = wind;
    }


    /**
     * No args constructor for use in serialization
     * 
     */
    public Current() {
    }

    /**
     * 
     * @param details
     * @param info
     */
    public Current(Info__1 info, Main__1 details) {
        super();
        this.info = info;
        this.details = details;
    }

    public Info__1 getInfo() {
        return info;
    }

    public void setInfo(Info__1 info) {
        this.info = info;
    }

    public Main__1 getMain() {
        return details;
    }

    public void setMain(Main__1 details) {
        this.details = details;
    }

}
