
package com.schemecode.zr3i.data.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsageDetails {

    @SerializedName("usage")
    @Expose
    private Usage usage;
    @SerializedName("meters_usage")
    @Expose
    private MetersUsage metersUsage;
    @SerializedName("meters_used")
    @Expose
    private MetersUsed metersUsed;
    @SerializedName("used")
    @Expose
    private Used used;
    @SerializedName("remaining")
    @Expose
    private Remaining remaining;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UsageDetails() {
    }

    /**
     * 
     * @param usage
     * @param used
     * @param metersUsage
     * @param remaining
     * @param metersUsed
     */
    public UsageDetails(Usage usage, MetersUsage metersUsage, MetersUsed metersUsed, Used used, Remaining remaining) {
        super();
        this.usage = usage;
        this.metersUsage = metersUsage;
        this.metersUsed = metersUsed;
        this.used = used;
        this.remaining = remaining;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public MetersUsage getMetersUsage() {
        return metersUsage;
    }

    public void setMetersUsage(MetersUsage metersUsage) {
        this.metersUsage = metersUsage;
    }

    public MetersUsed getMetersUsed() {
        return metersUsed;
    }

    public void setMetersUsed(MetersUsed metersUsed) {
        this.metersUsed = metersUsed;
    }

    public Used getUsed() {
        return used;
    }

    public void setUsed(Used used) {
        this.used = used;
    }

    public Remaining getRemaining() {
        return remaining;
    }

    public void setRemaining(Remaining remaining) {
        this.remaining = remaining;
    }

}
