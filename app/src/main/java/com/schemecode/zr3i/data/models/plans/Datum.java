
package com.schemecode.zr3i.data.models.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("usage")
    @Expose
    private Double usage;
    @SerializedName("usage_acres")
    @Expose
    private UsageAcres usageAcres;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public UsageAcres getUsageAcres() {
        return usageAcres;
    }

    public void setUsageAcres(UsageAcres usageAcres) {
        this.usageAcres = usageAcres;
    }

}
