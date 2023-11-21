
package com.schemecode.zr3i.data.models.update_pass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccUpdate {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("reset")
    @Expose
    private Boolean reset;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccUpdate() {
    }

    /**
     * 
     * @param result
     * @param alert
     * @param response
     * @param reset
     * @param message
     */
    public AccUpdate(String message, String alert, String response, Boolean result, Boolean reset) {
        super();
        this.message = message;
        this.alert = alert;
        this.response = response;
        this.result = result;
        this.reset = reset;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
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

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

}
