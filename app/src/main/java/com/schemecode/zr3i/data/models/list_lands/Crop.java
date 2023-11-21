
package com.schemecode.zr3i.data.models.list_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crop {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("code")
    @Expose
    private String code;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public Crop() {
    }

    /**
     * 
     * @param createdAt
     * @param code
     * @param updatedAtFormated
     * @param id
     * @param title
     * @param createdAtFormated
     * @param updatedAt
     */
    public Crop(Integer id, String title, String code, String createdAt, String updatedAt, String createdAtFormated, String updatedAtFormated) {
        super();
        this.id = id;
        this.title = title;
        this.code = code;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdAtFormated = createdAtFormated;
        this.updatedAtFormated = updatedAtFormated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
