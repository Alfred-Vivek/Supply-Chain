package com.example.supplychain.Request;
import com.google.gson.annotations.SerializedName;

public class LogisticAcceptRequest {
    @SerializedName("asnRefNumber")
    String asnRefNumber;
    @SerializedName("acceptance")
    String acceptance;
    @SerializedName("updatedby")
    String updatedby;
    @SerializedName("updatedDate")
    String updatedDate;
    @SerializedName("orgName")
    String orgName;
    @SerializedName("asnCreatedOrgID")
    String asnCreatedOrgID;
    @SerializedName("flag")
    String flag;

    public String getAsnRefNumber() {
        return asnRefNumber;
    }

    public void setAsnRefNumber(String asnRefNumber) {
        this.asnRefNumber = asnRefNumber;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAsnCreatedOrgID() {
        return asnCreatedOrgID;
    }

    public void setAsnCreatedOrgID(String asnCreatedOrgID) {
        this.asnCreatedOrgID = asnCreatedOrgID;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

