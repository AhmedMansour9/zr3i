
package com.schemecode.zr3i.data.models.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsageAcres {

    @SerializedName("full")
    @Expose
    private Double full;
    @SerializedName("rounded")
    @Expose
    private Integer rounded;
    @SerializedName("title")
    @Expose
    private String title;

    public Double getFull() {
        return full;
    }

    public void setFull(Double full) {
        this.full = full;
    }

    public Integer getRounded() {
        return rounded;
    }

    public void setRounded(Integer rounded) {
        this.rounded = rounded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
