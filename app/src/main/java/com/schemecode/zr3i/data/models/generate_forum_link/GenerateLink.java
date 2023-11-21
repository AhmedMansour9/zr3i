
package com.schemecode.zr3i.data.models.generate_forum_link;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateLink {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("data")
    @Expose
    private String data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GenerateLink() {
    }

    /**
     * 
     * @param result
     * @param data
     * @param success
     */
    public GenerateLink(Boolean success, Boolean result, String data) {
        super();
        this.success = success;
        this.result = result;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
