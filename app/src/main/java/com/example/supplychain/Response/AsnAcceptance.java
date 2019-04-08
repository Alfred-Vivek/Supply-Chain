package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AsnAcceptance implements Serializable {
    @SerializedName("acceptance")
    public String acceptance;
    @SerializedName("acceptedBy")
    public String acceptedBy;
    @SerializedName("acceptedDate")
    public String acceptedDate;
    @SerializedName("acceptedOrg")
    public String acceptedOrg;

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(String acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public String getAcceptedOrg() {
        return acceptedOrg;
    }

    public void setAcceptedOrg(String acceptedOrg) {
        this.acceptedOrg = acceptedOrg;
    }
}
