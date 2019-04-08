package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PickupDetails implements Serializable {
    @SerializedName("pickedBy")
    public String pickedBy;
    @SerializedName("pickedUpDate")
    public String pickedUpDate;
    @SerializedName("pickupOrg")
    public String pickupOrg;
    @SerializedName("pickupStatus")
    public String pickupStatus;

    public String getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(String pickedBy) {
        this.pickedBy = pickedBy;
    }

    public String getPickedUpDate() {
        return pickedUpDate;
    }

    public void setPickedUpDate(String pickedUpDate) {
        this.pickedUpDate = pickedUpDate;
    }

    public String getPickupOrg() {
        return pickupOrg;
    }

    public void setPickupOrg(String pickupOrg) {
        this.pickupOrg = pickupOrg;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }
}
