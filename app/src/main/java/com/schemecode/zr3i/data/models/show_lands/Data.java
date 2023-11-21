
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
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
    private Object desc;
    @SerializedName("map")
    @Expose
    private String map;
    @SerializedName("polygon_id")
    @Expose
    private Object polygonId;
    @SerializedName("analytics")
    @Expose
    private String analytics;
    @SerializedName("field_id")
    @Expose
    private String fieldId;
    @SerializedName("field")
    @Expose
    private Field field;
    @SerializedName("crop_id")
    @Expose
    private String cropId;
    @SerializedName("field_update")
    @Expose
    private String fieldUpdate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at_formated")
    @Expose
    private String createdAtFormated;
    @SerializedName("updated_at_formated")
    @Expose
    private String updatedAtFormated;
    @SerializedName("is_data_built")
    @Expose
    private Boolean isDataBuilt;
    @SerializedName("country_val")
    @Expose
    private String countryVal;
    @SerializedName("acre")
    @Expose
    private Acre acre;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("show_url")
    @Expose
    private String showUrl;
    @SerializedName("edit_url")
    @Expose
    private String editUrl;
    @SerializedName("farmers_url")
    @Expose
    private String farmersUrl;
    @SerializedName("map_url")
    @Expose
    private String mapUrl;
    @SerializedName("map_details")
    @Expose
    private MapDetails mapDetails;
    @SerializedName("field_index_area_image")
    @Expose
    private FieldIndexAreaImage fieldIndexAreaImage;
    @SerializedName("field_image")
    @Expose
    private FieldImage fieldImage;
    @SerializedName("field_report")
    @Expose
    private FieldReport fieldReport;
    @SerializedName("country")
    @Expose
    private Country country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param country
     * @param city
     * @param updatedAtFormated
     * @param showUrl
     * @param farmersUrl
     * @param countryId
     * @param mapDetails
     * @param actualSpace
     * @param analytics
     * @param createdAt
     * @param fieldReport
     * @param isDataBuilt
     * @param imageUrl
     * @param customerId
     * @param id
     * @param map
     * @param contractSpace
     * @param fieldId
     * @param updatedAt
     * @param owner
     * @param image
     * @param cropId
     * @param cityArea
     * @param fieldImage
     * @param cropPlantingCycles
     * @param countryVal
     * @param fieldUpdate
     * @param editUrl
     * @param field
     * @param polygonId
     * @param acre
     * @param mapUrl
     * @param fieldIndexAreaImage
     * @param createdAtFormated
     * @param desc
     */
    public Data(Integer id, String owner, Object image, Integer customerId, String countryId, String city, String cityArea, Integer contractSpace, Double actualSpace, Integer cropPlantingCycles, Object desc, String map, Object polygonId, String analytics, String fieldId, Field field, String cropId, String fieldUpdate, String createdAt, String updatedAt, String createdAtFormated, String updatedAtFormated, Boolean isDataBuilt, String countryVal, Acre acre, String imageUrl, String showUrl, String editUrl, String farmersUrl, String mapUrl, MapDetails mapDetails, FieldIndexAreaImage fieldIndexAreaImage, FieldImage fieldImage, FieldReport fieldReport, Country country) {
        super();
        this.id = id;
        this.owner = owner;
        this.image = image;
        this.customerId = customerId;
        this.countryId = countryId;
        this.city = city;
        this.cityArea = cityArea;
        this.contractSpace = contractSpace;
        this.actualSpace = actualSpace;
        this.cropPlantingCycles = cropPlantingCycles;
        this.desc = desc;
        this.map = map;
        this.polygonId = polygonId;
        this.analytics = analytics;
        this.fieldId = fieldId;
        this.field = field;
        this.cropId = cropId;
        this.fieldUpdate = fieldUpdate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdAtFormated = createdAtFormated;
        this.updatedAtFormated = updatedAtFormated;
        this.isDataBuilt = isDataBuilt;
        this.countryVal = countryVal;
        this.acre = acre;
        this.imageUrl = imageUrl;
        this.showUrl = showUrl;
        this.editUrl = editUrl;
        this.farmersUrl = farmersUrl;
        this.mapUrl = mapUrl;
        this.mapDetails = mapDetails;
        this.fieldIndexAreaImage = fieldIndexAreaImage;
        this.fieldImage = fieldImage;
        this.fieldReport = fieldReport;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
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

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Object getPolygonId() {
        return polygonId;
    }

    public void setPolygonId(Object polygonId) {
        this.polygonId = polygonId;
    }

    public String getAnalytics() {
        return analytics;
    }

    public void setAnalytics(String analytics) {
        this.analytics = analytics;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public String getFieldUpdate() {
        return fieldUpdate;
    }

    public void setFieldUpdate(String fieldUpdate) {
        this.fieldUpdate = fieldUpdate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAtFormated() {
        return createdAtFormated;
    }

    public void setCreatedAtFormated(String createdAtFormated) {
        this.createdAtFormated = createdAtFormated;
    }

    public String getUpdatedAtFormated() {
        return updatedAtFormated;
    }

    public void setUpdatedAtFormated(String updatedAtFormated) {
        this.updatedAtFormated = updatedAtFormated;
    }

    public Boolean getIsDataBuilt() {
        return isDataBuilt;
    }

    public void setIsDataBuilt(Boolean isDataBuilt) {
        this.isDataBuilt = isDataBuilt;
    }

    public String getCountryVal() {
        return countryVal;
    }

    public void setCountryVal(String countryVal) {
        this.countryVal = countryVal;
    }

    public Acre getAcre() {
        return acre;
    }

    public void setAcre(Acre acre) {
        this.acre = acre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getEditUrl() {
        return editUrl;
    }

    public void setEditUrl(String editUrl) {
        this.editUrl = editUrl;
    }

    public String getFarmersUrl() {
        return farmersUrl;
    }

    public void setFarmersUrl(String farmersUrl) {
        this.farmersUrl = farmersUrl;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public MapDetails getMapDetails() {
        return mapDetails;
    }

    public void setMapDetails(MapDetails mapDetails) {
        this.mapDetails = mapDetails;
    }

    public FieldIndexAreaImage getFieldIndexAreaImage() {
        return fieldIndexAreaImage;
    }

    public void setFieldIndexAreaImage(FieldIndexAreaImage fieldIndexAreaImage) {
        this.fieldIndexAreaImage = fieldIndexAreaImage;
    }

    public FieldImage getFieldImage() {
        return fieldImage;
    }

    public void setFieldImage(FieldImage fieldImage) {
        this.fieldImage = fieldImage;
    }

    public FieldReport getFieldReport() {
        return fieldReport;
    }

    public void setFieldReport(FieldReport fieldReport) {
        this.fieldReport = fieldReport;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
