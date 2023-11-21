
package com.schemecode.zr3i.data.models.edit_farmers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmersUpdate {

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
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FarmersUpdate() {
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
     * @param url
     */
    public FarmersUpdate(Boolean success, String lang, String platform, String response, Boolean result, String alert, List<Object> data, String message, String url) {
        super();
        this.success = success;
        this.lang = lang;
        this.platform = platform;
        this.response = response;
        this.result = result;
        this.alert = alert;
        this.data = data;
        this.message = message;
        this.url = url;
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

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
