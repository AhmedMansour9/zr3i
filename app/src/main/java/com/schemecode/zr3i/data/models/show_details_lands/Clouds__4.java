
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds__4 {

    @SerializedName("all")
    @Expose
    private Integer all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clouds__4() {
    }

    /**
     * 
     * @param all
     */
    public Clouds__4(Integer all) {
        super();
        this.all = all;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}