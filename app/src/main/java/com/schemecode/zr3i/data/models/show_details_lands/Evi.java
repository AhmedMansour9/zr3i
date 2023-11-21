
package com.schemecode.zr3i.data.models.show_details_lands;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Evi {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @SerializedName("values")
    @Expose
    private Object values;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Evi() {
    }

    /**
     * 
     * @param code
     * @param values
     * @param title
     */
    public Evi(String title, String code, Values__3 values) {
        super();
        this.title = title;
        this.code = code;
        this.values = values;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Values__3 values) {
        this.values = values;
    }

}
