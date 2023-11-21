
package com.schemecode.zr3i.data.models.show_farmers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("land_id")
    @Expose
    private Integer landId;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("national_id")
    @Expose
    private String nationalId;
    @SerializedName("city")
    @Expose
    private String city;
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
    @SerializedName("edit_farmer")
    @Expose
    private String editFarmer;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param address
     * @param city
     * @param updatedAtFormated
     * @param mobile
     * @param editFarmer
     * @param createdAt
     * @param landId
     * @param nationalId
     * @param name
     * @param id
     * @param age
     * @param email
     * @param createdAtFormated
     * @param updatedAt
     */
    public Datum(Integer id, String name, Integer landId, Integer age, String mobile, String address, String email, String nationalId, String city, String createdAt, String updatedAt, String createdAtFormated, String updatedAtFormated, String editFarmer) {
        super();
        this.id = id;
        this.name = name;
        this.landId = landId;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.nationalId = nationalId;
        this.city = city;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdAtFormated = createdAtFormated;
        this.updatedAtFormated = updatedAtFormated;
        this.editFarmer = editFarmer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getEditFarmer() {
        return editFarmer;
    }

    public void setEditFarmer(String editFarmer) {
        this.editFarmer = editFarmer;
    }

}
