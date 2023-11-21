
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Info {

    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("map_image_url")
    @Expose
    private String map_image_url;

    public String getMap_image_url() {
        return map_image_url;
    }

    public void setMap_image_url(String map_image_url) {
        this.map_image_url = map_image_url;
    }

    @SerializedName("contract_space")
    @Expose
    private String contractSpace;
    @SerializedName("contract_space_clear")
    @Expose
    private String contract_space_clear;
    @SerializedName("crop_planting_cycles")
    @Expose
    private Integer cropPlantingCycles;
    @SerializedName("actual_space")
    @Expose
    private Double actualSpace;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;
    @SerializedName("acre")
    @Expose
    private Acre acre;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("country_val")
    @Expose
    private String countryVal;
    @SerializedName("city_area")
    @Expose
    private String cityArea;
    @SerializedName("crop")
    @Expose
    private Crop crop;
    @SerializedName("planting_date")
    @Expose
    private String planting_date;

    public String getPlanting_date() {
        return planting_date;
    }

    public void setPlanting_date(String planting_date) {
        this.planting_date = planting_date;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Info() {
    }

    /**
     * 
     * @param owner
     * @param actualSpace
     * @param country
     * @param countryVal
     * @param city
     * @param cityArea
     * @param lastUpdate
     * @param acre
     * @param cropPlantingCycles
     * @param contractSpace
     * @param crop
     * @param desc
     */
    public Info(String owner, String contractSpace, Integer cropPlantingCycles, Double actualSpace, String lastUpdate, Acre acre, String desc, String city, Country country, String countryVal, String cityArea, Crop crop) {
        super();
        this.owner = owner;
        this.contractSpace = contractSpace;
        this.cropPlantingCycles = cropPlantingCycles;
        this.actualSpace = actualSpace;
        this.lastUpdate = lastUpdate;
        this.acre = acre;
        this.desc = desc;
        this.city = city;
        this.country = country;
        this.countryVal = countryVal;
        this.cityArea = cityArea;
        this.crop = crop;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContractSpace() {
        return contractSpace;
    }

    public void setContractSpace(String contractSpace) {
        this.contractSpace = contractSpace;
    }

    public Integer getCropPlantingCycles() {
        return cropPlantingCycles;
    }

    public void setCropPlantingCycles(Integer cropPlantingCycles) {
        this.cropPlantingCycles = cropPlantingCycles;
    }

    public Double getActualSpace() {
        return actualSpace;
    }

    public void setActualSpace(Double actualSpace) {
        this.actualSpace = actualSpace;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Acre getAcre() {
        return acre;
    }

    public void setAcre(Acre acre) {
        this.acre = acre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCountryVal() {
        return countryVal;
    }

    public void setCountryVal(String countryVal) {
        this.countryVal = countryVal;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public String getContract_space_clear() {
        return contract_space_clear;
    }

    public void setContract_space_clear(String contract_space_clear) {
        this.contract_space_clear = contract_space_clear;
    }
}
