
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HistoricalNdvi {

    @SerializedName("charts")
    @Expose
    private Charts charts;
    @SerializedName("days")
    @Expose
    private List<String> days = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HistoricalNdvi() {
    }

    /**
     * 
     * @param charts
     * @param days
     */
    public HistoricalNdvi(Charts charts, List<String> days) {
        super();
        this.charts = charts;
        this.days = days;
    }

    public Charts getCharts() {
        return charts;
    }

    public void setCharts(Charts charts) {
        this.charts = charts;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

}
