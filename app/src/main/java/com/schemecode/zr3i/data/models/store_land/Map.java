package com.schemecode.zr3i.data.models.store_land;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {

    @SerializedName("polygon")
    @Expose
    private List<com.schemecode.zr3i.data.classes.Polygon> polygon;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("zoom")
    @Expose
    private Float zoom;

    public List<com.schemecode.zr3i.data.classes.Polygon> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<com.schemecode.zr3i.data.classes.Polygon> polygon) {
        this.polygon = polygon;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Float getZoom() {
        return zoom;
    }

    public void setZoom(Float zoom) {
        this.zoom = zoom;
    }
}
