package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryDetails implements Serializable {
    @SerializedName("deliveredBy")
    public String deliveredBy;
    @SerializedName("deliveredOrg")
    public String deliveredOrg;
    @SerializedName("deliveryDate")
    public String deliveryDate;
    @SerializedName("deliveryStatus")
    public String deliveryStatus;

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public String getDeliveredOrg() {
        return deliveredOrg;
    }

    public void setDeliveredOrg(String deliveredOrg) {
        this.deliveredOrg = deliveredOrg;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
