
package com.schemecode.zr3i.data.models.plan_upgrade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccPlanUpgrade {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("upgrade_have_payment")
    @Expose
    private Boolean upgradeHavePayment;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccPlanUpgrade() {
    }

    /**
     * 
     * @param result
     * @param data
     * @param success
     * @param message
     * @param upgradeHavePayment
     */
    public AccPlanUpgrade(Boolean success, Boolean result, Data data, String message, Boolean upgradeHavePayment) {
        super();
        this.success = success;
        this.result = result;
        this.data = data;
        this.message = message;
        this.upgradeHavePayment = upgradeHavePayment;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getUpgradeHavePayment() {
        return upgradeHavePayment;
    }

    public void setUpgradeHavePayment(Boolean upgradeHavePayment) {
        this.upgradeHavePayment = upgradeHavePayment;
    }

}
