package com.schemecode.zr3i.data.models.store_land;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropField {
    @SerializedName("map")
    @Expose
    private Map map;
    @SerializedName("crop_id")
    @Expose
    private Integer cropId;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

}
