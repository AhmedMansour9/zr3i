package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherExpectation {
    private List<WeatherExpectationObject> weatherExpectationObjects = new ArrayList<>();

    public List<WeatherExpectationObject> getWeatherExpectationObjects() {
        return weatherExpectationObjects;
    }

    public void setWeatherExpectationObjects(List<WeatherExpectationObject> weatherExpectationObjects) {
        this.weatherExpectationObjects = weatherExpectationObjects;
    }
}
