
package com.schemecode.zr3i.data.models.usage;

import java.io.Serializable;
import java.util.List;
 import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

  public class Data implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("usage")
    @Expose
    private Double usage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("renewal_date")
    @Expose
    private String renewalDate;
    @SerializedName("price_title")
    @Expose
    private String priceTitle;
    @SerializedName("renewal_remaining_days")
    @Expose
    private Integer renewalRemainingDays;
    @SerializedName("renewal_remaining_days_read")
    @Expose
    private String renewalRemainingDaysRead;
    @SerializedName("usage_details")
    @Expose
    private UsageDetails usageDetails;
    @SerializedName("max_plans_price")
    @Expose
    private Integer maxPlansPrice;
    @SerializedName("is_renew_available")
    @Expose
    private Boolean isRenewAvailable;
    @SerializedName("is_upgrade_available")
    @Expose
    private Boolean isUpgradeAvailable;
    @SerializedName("plans_for_upgrade")
    @Expose
    private List<PlansForUpgrade> plansForUpgrade = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param renewalRemainingDaysRead
     * @param isRenewAvailable
     * @param plansForUpgrade
     * @param priceTitle
     * @param usage
     * @param renewalDate
     * @param title
     * @param isUpgradeAvailable
     * @param usageDetails
     * @param renewalRemainingDays
     * @param price
     * @param id
     * @param maxPlansPrice
     * @param desc
     */
    public Data(String title, String desc, Integer price, Double usage, Integer id, String renewalDate, String priceTitle, Integer renewalRemainingDays, String renewalRemainingDaysRead, UsageDetails usageDetails, Integer maxPlansPrice, Boolean isRenewAvailable, Boolean isUpgradeAvailable, List<PlansForUpgrade> plansForUpgrade) {
        super();
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.usage = usage;
        this.id = id;
        this.renewalDate = renewalDate;
        this.priceTitle = priceTitle;
        this.renewalRemainingDays = renewalRemainingDays;
        this.renewalRemainingDaysRead = renewalRemainingDaysRead;
        this.usageDetails = usageDetails;
        this.maxPlansPrice = maxPlansPrice;
        this.isRenewAvailable = isRenewAvailable;
        this.isUpgradeAvailable = isUpgradeAvailable;
        this.plansForUpgrade = plansForUpgrade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getPriceTitle() {
        return priceTitle;
    }

    public void setPriceTitle(String priceTitle) {
        this.priceTitle = priceTitle;
    }

    public Integer getRenewalRemainingDays() {
        return renewalRemainingDays;
    }

    public void setRenewalRemainingDays(Integer renewalRemainingDays) {
        this.renewalRemainingDays = renewalRemainingDays;
    }

    public String getRenewalRemainingDaysRead() {
        return renewalRemainingDaysRead;
    }

    public void setRenewalRemainingDaysRead(String renewalRemainingDaysRead) {
        this.renewalRemainingDaysRead = renewalRemainingDaysRead;
    }

    public UsageDetails getUsageDetails() {
        return usageDetails;
    }

    public void setUsageDetails(UsageDetails usageDetails) {
        this.usageDetails = usageDetails;
    }

    public Integer getMaxPlansPrice() {
        return maxPlansPrice;
    }

    public void setMaxPlansPrice(Integer maxPlansPrice) {
        this.maxPlansPrice = maxPlansPrice;
    }

    public Boolean getIsRenewAvailable() {
        return isRenewAvailable;
    }

    public void setIsRenewAvailable(Boolean isRenewAvailable) {
        this.isRenewAvailable = isRenewAvailable;
    }

    public Boolean getIsUpgradeAvailable() {
        return isUpgradeAvailable;
    }

    public void setIsUpgradeAvailable(Boolean isUpgradeAvailable) {
        this.isUpgradeAvailable = isUpgradeAvailable;
    }

    public List<PlansForUpgrade> getPlansForUpgrade() {
        return plansForUpgrade;
    }

    public void setPlansForUpgrade(List<PlansForUpgrade> plansForUpgrade) {
        this.plansForUpgrade = plansForUpgrade;
    }

}
