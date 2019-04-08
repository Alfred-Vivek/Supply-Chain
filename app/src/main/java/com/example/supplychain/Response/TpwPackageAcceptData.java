package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

public class TpwPackageAcceptData {
    @SerializedName("error")
    String error;
    @SerializedName("message")
    String message;
    @SerializedName("transactionID")
    String transactionID;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
