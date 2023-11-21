package com.schemecode.zr3i.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CsrfToken {

    @SerializedName("csrf_token")
    @Expose
    private String csrf_token;

    public String getCsrf_token() {
        return csrf_token;
    }

    public void setCsrf_token(String csrf_token) {
        this.csrf_token = csrf_token;
    }
}
