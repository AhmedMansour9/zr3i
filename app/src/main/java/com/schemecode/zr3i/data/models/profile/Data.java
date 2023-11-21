
package com.schemecode.zr3i.data.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("forum_id")
    @Expose
    private String forumId;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("token_web")
    @Expose
    private String tokenWeb;
    @SerializedName("token_android")
    @Expose
    private String tokenAndroid;
    @SerializedName("player_id")
    @Expose
    private Object playerId;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("email_verified_at")
    @Expose
    private String emailVerifiedAt;
    @SerializedName("account_type")
    @Expose
    private Integer accountType;
    @SerializedName("usage")
    @Expose
    private Double usage;
    @SerializedName("used")
    @Expose
    private Double used;
    @SerializedName("plan_id")
    @Expose
    private Integer planId;
    @SerializedName("plan_price")
    @Expose
    private Integer planPrice;
    @SerializedName("renewal_date")
    @Expose
    private String renewalDate;
    @SerializedName("payment_request")
    @Expose
    private String paymentRequest;
    @SerializedName("payment_request_data")
    @Expose
    private String paymentRequestData;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at_formated")
    @Expose
    private String createdAtFormated;
    @SerializedName("updated_at_formated")
    @Expose
    private String updatedAtFormated;
    @SerializedName("usage_details")
    @Expose
    private UsageDetails usageDetails;
    @SerializedName("image_url")
    @Expose
    private String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param lastName
     * @param gender
     * @param city
     * @param updatedAtFormated
     * @param usage
     * @param tokenAndroid
     * @param secret
     * @param used
     * @param countryId
     * @param usageDetails
     * @param createdAt
     * @param planId
     * @param id
     * @param planPrice
     * @param email
     * @param playerId
     * @param paymentRequest
     * @param updatedAt
     * @param image
     * @param address
     * @param accountType
     * @param mobile
     * @param renewalDate
     * @param birthDate
     * @param tokenWeb
     * @param firstName
     * @param emailVerifiedAt
     * @param paymentRequestData
     * @param forumId
     * @param createdAtFormated
     */
    public Data(Integer id, String firstName, String lastName, String forumId, Integer gender, String birthDate, Object address, String countryId, String city, String email, String mobile, Object image, String tokenWeb, String tokenAndroid, Object playerId, String secret, String emailVerifiedAt, Integer accountType, Double usage, Double used, Integer planId, Integer planPrice, String renewalDate, String paymentRequest, String paymentRequestData, String createdAt, String updatedAt, String createdAtFormated, String updatedAtFormated, UsageDetails usageDetails) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.forumId = forumId;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.countryId = countryId;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
        this.image = image;
        this.tokenWeb = tokenWeb;
        this.tokenAndroid = tokenAndroid;
        this.playerId = playerId;
        this.secret = secret;
        this.emailVerifiedAt = emailVerifiedAt;
        this.accountType = accountType;
        this.usage = usage;
        this.used = used;
        this.planId = planId;
        this.planPrice = planPrice;
        this.renewalDate = renewalDate;
        this.paymentRequest = paymentRequest;
        this.paymentRequestData = paymentRequestData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdAtFormated = createdAtFormated;
        this.updatedAtFormated = updatedAtFormated;
        this.usageDetails = usageDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getTokenWeb() {
        return tokenWeb;
    }

    public void setTokenWeb(String tokenWeb) {
        this.tokenWeb = tokenWeb;
    }

    public String getTokenAndroid() {
        return tokenAndroid;
    }

    public void setTokenAndroid(String tokenAndroid) {
        this.tokenAndroid = tokenAndroid;
    }

    public Object getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Object playerId) {
        this.playerId = playerId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Integer planPrice) {
        this.planPrice = planPrice;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(String paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public String getPaymentRequestData() {
        return paymentRequestData;
    }

    public void setPaymentRequestData(String paymentRequestData) {
        this.paymentRequestData = paymentRequestData;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAtFormated() {
        return createdAtFormated;
    }

    public void setCreatedAtFormated(String createdAtFormated) {
        this.createdAtFormated = createdAtFormated;
    }

    public String getUpdatedAtFormated() {
        return updatedAtFormated;
    }

    public void setUpdatedAtFormated(String updatedAtFormated) {
        this.updatedAtFormated = updatedAtFormated;
    }

    public UsageDetails getUsageDetails() {
        return usageDetails;
    }

    public void setUsageDetails(UsageDetails usageDetails) {
        this.usageDetails = usageDetails;
    }

}
