
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Acre {

    @SerializedName("full")
    @Expose
    private Double full;
    @SerializedName("rounded")
    @Expose
    private Double rounded;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Acre() {
    }

    /**
     * 
     * @param rounded
     * @param title
     * @param full
     */
    public Acre(Double full, Double rounded, String title) {
        super();
        this.full = full;
        this.rounded = rounded;
        this.title = title;
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

}
