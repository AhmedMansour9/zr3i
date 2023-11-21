
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("Paid")
    @Expose
    private String paid;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("GenTif")
    @Expose
    private String genTif;
    @SerializedName("Health")
    @Expose
    private Object health;
    @SerializedName("hUnits")
    @Expose
    private Integer hUnits;
    @SerializedName("FieldID")
    @Expose
    private String fieldID;
    @SerializedName("SARDays")
    @Expose
    private SARDays sARDays;
    @SerializedName("Weather")
    @Expose
    private Weather weather;
    @SerializedName("Expiring")
    @Expose
    private String expiring;
    @SerializedName("CenterLat")
    @Expose
    private Double centerLat;
    @SerializedName("FieldArea")
    @Expose
    private Integer fieldArea;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("CenterLong")
    @Expose
    private Double centerLong;
    @SerializedName("SensedDays")
    @Expose
    private SensedDays sensedDays;
    @SerializedName("Coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("FieldLatLen")
    @Expose
    private Double fieldLatLen;
    @SerializedName("FieldMaxLat")
    @Expose
    private Double fieldMaxLat;
    @SerializedName("FieldMinLat")
    @Expose
    private Double fieldMinLat;
    @SerializedName("PaymentType")
    @Expose
    private String paymentType;
    @SerializedName("FieldAddress")
    @Expose
    private String fieldAddress;
    @SerializedName("FieldLongLen")
    @Expose
    private Double fieldLongLen;
    @SerializedName("FieldMaxLong")
    @Expose
    private Double fieldMaxLong;
    @SerializedName("FieldMinLong")
    @Expose
    private Double fieldMinLong;
    @SerializedName("CenterLatLarge")
    @Expose
    private Double centerLatLarge;
    @SerializedName("CenterLongLarge")
    @Expose
    private Double centerLongLarge;
    @SerializedName("FieldLatLenLarge")
    @Expose
    private Double fieldLatLenLarge;
    @SerializedName("FieldLongLenLarge")
    @Expose
    private Double fieldLongLenLarge;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Field() {
    }

    /**
     * 
     * @param expiring
     * @param fieldArea
     * @param sensedDays
     * @param fieldMinLat
     * @param fieldLongLenLarge
     * @param fieldMaxLong
     * @param centerLong
     * @param paymentType
     * @param fieldLatLenLarge
     * @param uid
     * @param fieldMaxLat
     * @param centerLatLarge
     * @param fieldMinLong
     * @param weather
     * @param centerLongLarge
     * @param email
     * @param sARDays
     * @param genTif
     * @param coordinates
     * @param health
     * @param fieldAddress
     * @param paid
     * @param fieldLatLen
     * @param centerLat
     * @param fieldLongLen
     * @param orderDate
     * @param hUnits
     * @param fieldID
     */
    public Field(String uid, String paid, String email, String genTif, Health health, Integer hUnits, String fieldID, SARDays sARDays, Weather weather, String expiring, Double centerLat, Integer fieldArea, String orderDate, Double centerLong, SensedDays sensedDays, Coordinates coordinates, Double fieldLatLen, Double fieldMaxLat, Double fieldMinLat, String paymentType, String fieldAddress, Double fieldLongLen, Double fieldMaxLong, Double fieldMinLong, Double centerLatLarge, Double centerLongLarge, Double fieldLatLenLarge, Double fieldLongLenLarge) {
        super();
        this.uid = uid;
        this.paid = paid;
        this.email = email;
        this.genTif = genTif;
        this.health = health;
        this.hUnits = hUnits;
        this.fieldID = fieldID;
        this.sARDays = sARDays;
        this.weather = weather;
        this.expiring = expiring;
        this.centerLat = centerLat;
        this.fieldArea = fieldArea;
        this.orderDate = orderDate;
        this.centerLong = centerLong;
        this.sensedDays = sensedDays;
        this.coordinates = coordinates;
        this.fieldLatLen = fieldLatLen;
        this.fieldMaxLat = fieldMaxLat;
        this.fieldMinLat = fieldMinLat;
        this.paymentType = paymentType;
        this.fieldAddress = fieldAddress;
        this.fieldLongLen = fieldLongLen;
        this.fieldMaxLong = fieldMaxLong;
        this.fieldMinLong = fieldMinLong;
        this.centerLatLarge = centerLatLarge;
        this.centerLongLarge = centerLongLarge;
        this.fieldLatLenLarge = fieldLatLenLarge;
        this.fieldLongLenLarge = fieldLongLenLarge;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenTif() {
        return genTif;
    }

    public void setGenTif(String genTif) {
        this.genTif = genTif;
    }

    public Object getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Integer gethUnits() {
        return hUnits;
    }

    public void sethUnits(Integer hUnits) {
        this.hUnits = hUnits;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public SARDays getSARDays() {
        return sARDays;
    }

    public void setSARDays(SARDays sARDays) {
        this.sARDays = sARDays;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getExpiring() {
        return expiring;
    }

    public void setExpiring(String expiring) {
        this.expiring = expiring;
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(Double centerLat) {
        this.centerLat = centerLat;
    }

    public Integer getFieldArea() {
        return fieldArea;
    }

    public void setFieldArea(Integer fieldArea) {
        this.fieldArea = fieldArea;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getCenterLong() {
        return centerLong;
    }

    public void setCenterLong(Double centerLong) {
        this.centerLong = centerLong;
    }

    public SensedDays getSensedDays() {
        return sensedDays;
    }

    public void setSensedDays(SensedDays sensedDays) {
        this.sensedDays = sensedDays;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Double getFieldLatLen() {
        return fieldLatLen;
    }

    public void setFieldLatLen(Double fieldLatLen) {
        this.fieldLatLen = fieldLatLen;
    }

    public Double getFieldMaxLat() {
        return fieldMaxLat;
    }

    public void setFieldMaxLat(Double fieldMaxLat) {
        this.fieldMaxLat = fieldMaxLat;
    }

    public Double getFieldMinLat() {
        return fieldMinLat;
    }

    public void setFieldMinLat(Double fieldMinLat) {
        this.fieldMinLat = fieldMinLat;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFieldAddress() {
        return fieldAddress;
    }

    public void setFieldAddress(String fieldAddress) {
        this.fieldAddress = fieldAddress;
    }

    public Double getFieldLongLen() {
        return fieldLongLen;
    }

    public void setFieldLongLen(Double fieldLongLen) {
        this.fieldLongLen = fieldLongLen;
    }

    public Double getFieldMaxLong() {
        return fieldMaxLong;
    }

    public void setFieldMaxLong(Double fieldMaxLong) {
        this.fieldMaxLong = fieldMaxLong;
    }

    public Double getFieldMinLong() {
        return fieldMinLong;
    }

    public void setFieldMinLong(Double fieldMinLong) {
        this.fieldMinLong = fieldMinLong;
    }

    public Double getCenterLatLarge() {
        return centerLatLarge;
    }

    public void setCenterLatLarge(Double centerLatLarge) {
        this.centerLatLarge = centerLatLarge;
    }

    public Double getCenterLongLarge() {
        return centerLongLarge;
    }

    public void setCenterLongLarge(Double centerLongLarge) {
        this.centerLongLarge = centerLongLarge;
    }

    public Double getFieldLatLenLarge() {
        return fieldLatLenLarge;
    }

    public void setFieldLatLenLarge(Double fieldLatLenLarge) {
        this.fieldLatLenLarge = fieldLatLenLarge;
    }

    public Double getFieldLongLenLarge() {
        return fieldLongLenLarge;
    }

    public void setFieldLongLenLarge(Double fieldLongLenLarge) {
        this.fieldLongLenLarge = fieldLongLenLarge;
    }

}
