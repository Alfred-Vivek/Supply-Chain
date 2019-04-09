package com.example.supplychain.Response;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
public class LogisticPackageResponse implements Serializable{
    @SerializedName("data")
    List<LogisticPackageData> logisticPackageData;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() { return status;  }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<LogisticPackageData> getLogisticPackageData() {
        return logisticPackageData;
    }
    public void setLogisticPackageData(List<LogisticPackageData> logisticPackageData) {
        this.logisticPackageData = logisticPackageData;
    }
}

