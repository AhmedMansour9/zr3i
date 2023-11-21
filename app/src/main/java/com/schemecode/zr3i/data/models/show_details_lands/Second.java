
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Second {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("data")
    @Expose
    private List<Datum__1> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Second() {
    }

    /**
     * 
     * @param data
     * @param title
     */
    public Second(String title, List<Datum__1> data) {
        super();
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Datum__1> getData() {
        return data;
    }

    public void setData(List<Datum__1> data) {
        this.data = data;
    }

}
