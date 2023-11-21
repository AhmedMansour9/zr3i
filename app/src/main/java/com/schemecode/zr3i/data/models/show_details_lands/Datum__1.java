
package com.schemecode.zr3i.data.models.show_details_lands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum__1 {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("values")
    @Expose
    private List<Double> values = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum__1() {
    }

    /**
     * 
     * @param values
     * @param title
     */
    public Datum__1(String title, List<Double> values) {
        super();
        this.title = title;
        this.values = values;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

}
