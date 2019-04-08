package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    String status;
    @SerializedName("email")
    String email;
    @SerializedName("dcOrg")
    String dcOrg;
    @SerializedName("orgTypeID")
    String orgTypeID;
    @SerializedName("orgTypeName")
    String orgTypeName;
    @SerializedName("siteCode")
    String siteCode;
    @SerializedName("name")
    String name;
    @SerializedName("auth")
    String auth;
    @SerializedName("token")
    String token;

    public String getStatus() {
        return status;
    }
    public String getEmail() {
        return email;
    }
    public String getDcOrg() {
        return dcOrg;
    }
    public String getOrgTypeID() { return orgTypeID; }
    public String getOrgTypeName() { return orgTypeName; }
    public String getSiteCode() {
        return siteCode;
    }
    public String getName() {
        return name;
    }
    public String getAuth() { return auth; }
    public String getToken() {
        return token;
    }
}
