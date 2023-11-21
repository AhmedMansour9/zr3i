
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class A {

    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;

    /**
     * No args constructor for use in serialization
     * 
     */
    public A() {
    }

    /**
     * 
     * @param latitude
     * @param longitude
     */
    public A(String latitude, String longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
