
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Weather {

    @SerializedName("current")
    @Expose
    private Current current;
    @SerializedName("forcasting")
    @Expose
    private Forcasting forcasting;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Weather() {
    }

    /**
     * 
     * @param current
     * @param forcasting
     */
    public Weather(Current current, Forcasting forcasting) {
        super();
        this.current = current;
        this.forcasting = forcasting;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forcasting getForcasting() {
        return forcasting;
    }

    public void setForcasting(Forcasting forcasting) {
        this.forcasting = forcasting;
    }

}
