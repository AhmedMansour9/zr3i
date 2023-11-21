
package com.schemecode.zr3i.data.models.usage;

 import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 import java.io.Serializable;

public class PlansForUpgrade implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("usage")
    @Expose
    private Double usage;
    @SerializedName("desc")
    @Expose
    private String desc;
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
    @SerializedName("usage_acres")
    @Expose
    private UsageAcres usageAcres;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlansForUpgrade() {
    }

    /**
     * 
     * @param createdAt
     * @param usageAcres
     * @param updatedAtFormated
     * @param price
     * @param usage
     * @param id
     * @param title
     * @param createdAtFormated
     * @param desc
     * @param updatedAt
     */
    public PlansForUpgrade(Integer id, String title, Integer price, Double usage, String desc, String createdAt, String updatedAt, String createdAtFormated, String updatedAtFormated, UsageAcres usageAcres) {
        super();
        this.id = id;
        this.title = title;
        this.price = price;
        this.usage = usage;
        this.desc = desc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdAtFormated = createdAtFormated;
        this.updatedAtFormated = updatedAtFormated;
        this.usageAcres = usageAcres;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public UsageAcres getUsageAcres() {
        return usageAcres;
    }

    public void setUsageAcres(UsageAcres usageAcres) {
        this.usageAcres = usageAcres;
    }

}
