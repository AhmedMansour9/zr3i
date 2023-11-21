package com.schemecode.zr3i.data.models.store_land;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreLandRequest {
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("city_area")
    @Expose
    private String cityArea;
    @SerializedName("contract_space")
    @Expose
    private Integer contractSpace;
    @SerializedName("actual_space")
    @Expose
    private Double actualSpace;
    @SerializedName("crop_planting_cycles")
    @Expose
    private Integer cropPlantingCycles;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("crop_id")
    @Expose
    private Integer cropId;
    @SerializedName("crop_fields")
    @Expose
    private List<CropField> cropFields;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public Integer getContractSpace() {
        return contractSpace;
    }

    public void setContractSpace(Integer contractSpace) {
        this.contractSpace = contractSpace;
    }

    public Double getActualSpace() {
        return actualSpace;
    }

    public void setActualSpace(Double actualSpace) {
        this.actualSpace = actualSpace;
    }

    public Integer getCropPlantingCycles() {
        return cropPlantingCycles;
    }

    public void setCropPlantingCycles(Integer cropPlantingCycles) {
        this.cropPlantingCycles = cropPlantingCycles;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public List<CropField> getCropFields() {
        return cropFields;
    }

    public void setCropFields(List<CropField> cropFields) {
        this.cropFields = cropFields;
    }
}
