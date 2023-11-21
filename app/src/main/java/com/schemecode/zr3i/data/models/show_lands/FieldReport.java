
package com.schemecode.zr3i.data.models.show_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldReport {

    @SerializedName("exists")
    @Expose
    private Boolean exists;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FieldReport() {
    }

    /**
     * 
     * @param exists
     * @param url
     */
    public FieldReport(Boolean exists, String url) {
        super();
        this.exists = exists;
        this.url = url;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
