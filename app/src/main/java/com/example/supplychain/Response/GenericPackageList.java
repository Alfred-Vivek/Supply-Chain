package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GenericPackageList implements Serializable {
    @SerializedName("height")
    public String height;
    @SerializedName("itemName")
    public String itemName;
    @SerializedName("length")
    public String length;
    @SerializedName("packageAddedDate")
    public String packageAddedDate;
    @SerializedName("packageInfoAddedBy")
    public String packageInfoAddedBy;
    @SerializedName("quantity")
    public String quantity;
    @SerializedName("width")
    public String width;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPackageAddedDate() {
        return packageAddedDate;
    }

    public void setPackageAddedDate(String packageAddedDate) {
        this.packageAddedDate = packageAddedDate;
    }

    public String getPackageInfoAddedBy() {
        return packageInfoAddedBy;
    }

    public void setPackageInfoAddedBy(String packageInfoAddedBy) {
        this.packageInfoAddedBy = packageInfoAddedBy;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}