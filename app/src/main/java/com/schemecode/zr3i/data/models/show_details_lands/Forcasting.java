
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Forcasting {

    @SerializedName("by_day_times")
    @Expose
    private Object byDayTimes;
    @SerializedName("by_days")
    @Expose
    private Object byDays;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Forcasting() {
    }

    /**
     * 
     * @param byDays
     * @param byDayTimes
     */
    public Forcasting(ByDayTimes byDayTimes, ByDays byDays) {
        super();
        this.byDayTimes = byDayTimes;
        this.byDays = byDays;
    }

    public Object getByDayTimes() {
        return byDayTimes;
    }

    public void setByDayTimes(ByDayTimes byDayTimes) {
        this.byDayTimes = byDayTimes;
    }

    public Object getByDays() {
        return byDays;
    }

    public void setByDays(ByDays byDays) {
        this.byDays = byDays;
    }

}
