package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

public class LogisticPackageAcceptData {
    @SerializedName("error")
    public String error;
    @SerializedName("message")
    public String message;
    @SerializedName("transactionID")
    public String transactionID;

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
