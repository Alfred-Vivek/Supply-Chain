package com.example.supplychain.Response;
import com.google.gson.annotations.SerializedName;

public class LogisticPackageAcceptResponse {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    LogisticPackageAcceptData logisticPackageAcceptData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LogisticPackageAcceptData getLogisticPackageAcceptData() {
        return logisticPackageAcceptData;
    }

    public void setLogisticPackageAcceptData(LogisticPackageAcceptData logisticPackageAcceptData) {
        this.logisticPackageAcceptData = logisticPackageAcceptData;
    }
}
