package com.example.supplychain.Response;
import com.google.gson.annotations.SerializedName;

public class TpwPackageAcceptResponse {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    TpwPackageAcceptData tpwPackageAcceptData;

    public TpwPackageAcceptData getTpwPackageAcceptData() {
        return tpwPackageAcceptData;
    }
    public void setTpwPackageAcceptData(TpwPackageAcceptData tpwPackageAcceptData) {
        this.tpwPackageAcceptData = tpwPackageAcceptData;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
