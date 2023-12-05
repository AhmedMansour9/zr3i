
package com.schemecode.zr3i.data.models.show_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapDetails {

    @SerializedName("zoom")
    @Expose
    private String zoom;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("polygon")
    @Expose
    private List<Polygon> polygon = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MapDetails() {
    }

    /**
     *
     * @param polygon
     * @param latitude
     * @param zoom
     * @param longitude
     */
    public MapDetails(String zoom, String latitude, String longitude, List<Polygon> polygon) {
        super();
        this.zoom = zoom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.polygon = polygon;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
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

    public List<Polygon> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<Polygon> polygon) {
        this.polygon = polygon;
    }

}
