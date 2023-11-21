
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Current {

    @SerializedName("info")
    @Expose
    private Info__1 info;
    @SerializedName("details")
    @Expose
    private Details details;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Current() {
    }

    /**
     * 
     * @param details
     * @param info
     */
    public Current(Info__1 info, Details details) {
        super();
        this.info = info;
        this.details = details;
    }

    public Info__1 getInfo() {
        return info;
    }

    public void setInfo(Info__1 info) {
        this.info = info;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

}
