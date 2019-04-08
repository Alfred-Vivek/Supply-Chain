package com.example.supplychain.Request;

import com.google.gson.annotations.SerializedName;

public class TpwAcceptRequest {
    @SerializedName("tpwKey")
    String tpwKey;
    @SerializedName("flag")
    String flag;
    @SerializedName("acceptedBy")
    String acceptedBy;
    @SerializedName("orgName")
    String orgName;

    public String getTpwKey() {
        return tpwKey;
    }

    public void setTpwKey(String tpwKey) {
        this.tpwKey = tpwKey;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
