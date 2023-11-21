package com.schemecode.zr3i.data.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatLngClass {

    private String latLng ;

    public LatLngClass(String latLng) {
        this.latLng = latLng;
    }

    public String getLat() {
        return latLng;
    }

    public void setLat(String latLng) {
        this.latLng = latLng;
    }
}
