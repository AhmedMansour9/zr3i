package com.schemecode.zr3i.data.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("by_day_times")
    @Expose
    private List<ByDayTimeResponse> byDayTimes;

    public List<ByDayTimeResponse> getByDayTimes() {
        return byDayTimes;
    }

    public void setByDayTimes(List<ByDayTimeResponse> byDayTimes) {
        this.byDayTimes = byDayTimes;
    }
}
