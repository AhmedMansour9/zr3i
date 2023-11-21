
package com.schemecode.zr3i.data.models.show_farmers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmersList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FarmersList() {
    }

    /**
     * 
     * @param result
     * @param data
     * @param alert
     * @param success
     * @param response
     * @param lang
     * @param message
     * @param platform
     */
    public FarmersList(Boolean success, String lang, String platform, String response, Boolean result, String alert, String message, List<Datum> data) {
        super();
        this.success = success;
        this.lang = lang;
        this.platform = platform;
        this.response = response;
        this.result = result;
        this.alert = alert;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
