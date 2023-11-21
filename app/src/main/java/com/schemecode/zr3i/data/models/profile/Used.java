
package com.schemecode.zr3i.data.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Used {

    @SerializedName("full")
    @Expose
    private Double full;
    @SerializedName("rounded")
    @Expose
    private Double rounded;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("percent")
    @Expose
    private Double percent;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Used() {
    }

    /**
     * 
     * @param rounded
     * @param title
     * @param percent
     * @param full
     */
    public Used(Double full, Double rounded, String title, Double percent) {
        super();
        this.full = full;
        this.rounded = rounded;
        this.title = title;
        this.percent = percent;
    }

    public Double getFull() {
        return full;
    }

    public void setFull(Double full) {
        this.full = full;
    }

    public Double getRounded() {
        return rounded;
    }

    public void setRounded(Double rounded) {
        this.rounded = rounded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

}
