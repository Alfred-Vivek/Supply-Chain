package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TpwPackageResponse implements Serializable {
    @SerializedName("data")
    List <TpwpackageData> tpwpackageData;
    @SerializedName("status")
    String Status;
    @SerializedName("message")
    String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TpwpackageData> getTpwpackageData() {
        return tpwpackageData;
    }

    public void setTpwpackageData(List<TpwpackageData> tpwpackageData) {
        this.tpwpackageData = tpwpackageData;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
