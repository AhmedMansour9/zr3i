
package com.schemecode.zr3i.data.models.plan_renew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("payment_url")
    @Expose
    private String paymentUrl;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param amount
     * @param paymentUrl
     */
    public Data(String paymentUrl, Integer amount) {
        super();
        this.paymentUrl = paymentUrl;
        this.amount = amount;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
