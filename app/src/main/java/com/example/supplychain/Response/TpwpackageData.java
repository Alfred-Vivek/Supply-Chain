package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TpwpackageData implements Serializable {
    @SerializedName("awbNumber")
    String awbNumber;
    @SerializedName("createdDate")
    String createdDate;
    @SerializedName("createdOrgID")
    String createdOrgID;
    @SerializedName("createdOrgName")
    String createdOrgName;
    @SerializedName("docType")
    String docType;
    @SerializedName("documentHash")
    String documentHash;
    @SerializedName("documentRefURL")
    String documentRefURL;
    @SerializedName("estimatedArrival")
    String estimatedArrival;
    @SerializedName("reqReleaseDate")
    String reqReleaseDate;
    @SerializedName("reqStorageDays")
    String reqStorageDays;
    @SerializedName("reqStorageVolume")
    String reqStorageVolume;
    @SerializedName("tpwID")
    String tpwID;
    @SerializedName("tpwName")
    String tpwName;
    @SerializedName("shiptoLocationAddress")
    String shiptoLocationAddress;
    @SerializedName("totalLength")
    String totalLength;
    @SerializedName("totalHeight")
    String totalHeight;
    @SerializedName("totalwidth")
    String totalwidth;
    @SerializedName("totalQuantity")
    String totalQuantity;
    @SerializedName("packageInfo")
    List <GenericPackageList> genericPackageList;

    public String getAwbNumber() {
        return awbNumber;
    }

    public void setAwbNumber(String awbNumber) {
        this.awbNumber = awbNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedOrgID() {
        return createdOrgID;
    }

    public void setCreatedOrgID(String createdOrgID) {
        this.createdOrgID = createdOrgID;
    }

    public String getCreatedOrgName() {
        return createdOrgName;
    }

    public void setCreatedOrgName(String createdOrgName) {
        this.createdOrgName = createdOrgName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getDocumentRefURL() {
        return documentRefURL;
    }

    public void setDocumentRefURL(String documentRefURL) {
        this.documentRefURL = documentRefURL;
    }

    public String getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(String estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public String getReqReleaseDate() {
        return reqReleaseDate;
    }

    public void setReqReleaseDate(String reqReleaseDate) {
        this.reqReleaseDate = reqReleaseDate;
    }

    public String getReqStorageDays() {
        return reqStorageDays;
    }

    public void setReqStorageDays(String reqStorageDays) {
        this.reqStorageDays = reqStorageDays;
    }

    public String getReqStorageVolume() {
        return reqStorageVolume;
    }

    public void setReqStorageVolume(String reqStorageVolume) {
        this.reqStorageVolume = reqStorageVolume;
    }

    public String getTpwID() {
        return tpwID;
    }

    public void setTpwID(String tpwID) {
        this.tpwID = tpwID;
    }

    public String getTpwName() {
        return tpwName;
    }

    public void setTpwName(String tpwName) {
        this.tpwName = tpwName;
    }

    public String getShiptoLocationAddress() {
        return shiptoLocationAddress;
    }

    public void setShiptoLocationAddress(String shiptoLocationAddress) {
        this.shiptoLocationAddress = shiptoLocationAddress;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public String getTotalHeight() {
        return totalHeight;
    }

    public void setTotalHeight(String totalHeight) {
        this.totalHeight = totalHeight;
    }

    public String getTotalwidth() {
        return totalwidth;
    }

    public void setTotalwidth(String totalwidth) {
        this.totalwidth = totalwidth;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<GenericPackageList> getGenericPackageList() {
        return genericPackageList;
    }

    public void setGenericPackageList(List<GenericPackageList> genericPackageList) {
        this.genericPackageList = genericPackageList;
    }
}
