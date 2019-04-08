package com.example.supplychain.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LogisticPackageData implements Serializable {
    @SerializedName("asnCreatedBy")
    public String asnCreatedBy;
    @SerializedName("asnCreatedDate")
    public String asnCreatedDate;
    @SerializedName("asnCreatedOrgID")
    public String asnCreatedOrgID;
    @SerializedName("asnCreatedOrgName")
    public String asnCreatedOrgName;
    @SerializedName("asnCreatedSite")
    public String asnCreatedSite;
    @SerializedName("asnNumber")
    public String asnNumber;
    @SerializedName("asnRefNumber")
    public String asnRefNumber;
    @SerializedName("awbNumber")
    public String awbNumber;
    @SerializedName("billofLadingNumber")
    public String billofLadingNumber;
    @SerializedName("docType")
    public String docType;
    @SerializedName("documentDate")
    public String documentDate;
    @SerializedName("documentHash")
    public String documentHash;
    @SerializedName("documentRefURL")
    public String documentRefURL;
    @SerializedName("leadDays")
    public String leadDays;
    @SerializedName("packingSlipNumber")
    public String packingSlipNumber;
    @SerializedName("portofArrival")
    public String portofArrival;
    @SerializedName("portofDeparture")
    public String portofDeparture;
    @SerializedName("requestLeadDays")
    public String requestLeadDays;
    @SerializedName("requestPickupdate")
    public String requestPickupdate;
    @SerializedName("shipmentDate")
    public String shipmentDate;
    @SerializedName("shiptoLocation")
    public String shiptoLocation;
    @SerializedName("toOrgID")
    public String toOrgID;
    @SerializedName("toOrgName")
    public String toOrgName;
    @SerializedName("shiptoLocationAddress")
    public String shiptoLocationAddress;
    @SerializedName("totalLength")
    public String totalLength;
    @SerializedName("totalHeight")
    public String totalHeight;
    @SerializedName("totalwidth")
    public String totalwidth;
    @SerializedName("totalQuantity")
    public String totalQuantity;
    @SerializedName("packageInfo")
    public List<GenericPackageList> genericPackageLists;
    @SerializedName("asnAcceptance")
    public AsnAcceptance asnAcceptance;
    @SerializedName("deliveryDetails")
    public DeliveryDetails deliveryDetails;
    @SerializedName("pickupDetails")
    public PickupDetails pickupDetails;

    public String getAsnCreatedBy() {
        return asnCreatedBy;
    }

    public void setAsnCreatedBy(String asnCreatedBy) {
        this.asnCreatedBy = asnCreatedBy;
    }

    public String getAsnCreatedDate() {
        return asnCreatedDate;
    }

    public void setAsnCreatedDate(String asnCreatedDate) {
        this.asnCreatedDate = asnCreatedDate;
    }

    public String getAsnCreatedOrgID() {
        return asnCreatedOrgID;
    }

    public void setAsnCreatedOrgID(String asnCreatedOrgID) {
        this.asnCreatedOrgID = asnCreatedOrgID;
    }

    public String getAsnCreatedOrgName() {
        return asnCreatedOrgName;
    }

    public void setAsnCreatedOrgName(String asnCreatedOrgName) {
        this.asnCreatedOrgName = asnCreatedOrgName;
    }

    public String getAsnCreatedSite() {
        return asnCreatedSite;
    }

    public void setAsnCreatedSite(String asnCreatedSite) {
        this.asnCreatedSite = asnCreatedSite;
    }

    public String getAsnNumber() {
        return asnNumber;
    }

    public void setAsnNumber(String asnNumber) {
        this.asnNumber = asnNumber;
    }

    public String getAsnRefNumber() {
        return asnRefNumber;
    }

    public void setAsnRefNumber(String asnRefNumber) {
        this.asnRefNumber = asnRefNumber;
    }

    public String getAwbNumber() {
        return awbNumber;
    }

    public void setAwbNumber(String awbNumber) {
        this.awbNumber = awbNumber;
    }

    public String getBillofLadingNumber() {
        return billofLadingNumber;
    }

    public void setBillofLadingNumber(String billofLadingNumber) {
        this.billofLadingNumber = billofLadingNumber;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
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

    public String getLeadDays() {
        return leadDays;
    }

    public void setLeadDays(String leadDays) {
        this.leadDays = leadDays;
    }

    public String getPackingSlipNumber() {
        return packingSlipNumber;
    }

    public void setPackingSlipNumber(String packingSlipNumber) {
        this.packingSlipNumber = packingSlipNumber;
    }

    public String getPortofArrival() {
        return portofArrival;
    }

    public void setPortofArrival(String portofArrival) {
        this.portofArrival = portofArrival;
    }

    public String getPortofDeparture() {
        return portofDeparture;
    }

    public void setPortofDeparture(String portofDeparture) {
        this.portofDeparture = portofDeparture;
    }

    public String getRequestLeadDays() {
        return requestLeadDays;
    }

    public void setRequestLeadDays(String requestLeadDays) {
        this.requestLeadDays = requestLeadDays;
    }

    public String getRequestPickupdate() {
        return requestPickupdate;
    }

    public void setRequestPickupdate(String requestPickupdate) {
        this.requestPickupdate = requestPickupdate;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShiptoLocation() {
        return shiptoLocation;
    }

    public void setShiptoLocation(String shiptoLocation) {
        this.shiptoLocation = shiptoLocation;
    }

    public String getToOrgID() {
        return toOrgID;
    }

    public void setToOrgID(String toOrgID) {
        this.toOrgID = toOrgID;
    }

    public String getToOrgName() {
        return toOrgName;
    }

    public void setToOrgName(String toOrgName) {
        this.toOrgName = toOrgName;
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

    public List<GenericPackageList> getGenericPackageLists() {
        return genericPackageLists;
    }

    public void setGenericPackageLists(List<GenericPackageList> genericPackageLists) {
        this.genericPackageLists = genericPackageLists;
    }

    public AsnAcceptance getAsnAcceptance() {
        return asnAcceptance;
    }

    public void setAsnAcceptance(AsnAcceptance asnAcceptance) {
        this.asnAcceptance = asnAcceptance;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public PickupDetails getPickupDetails() {
        return pickupDetails;
    }

    public void setPickupDetails(PickupDetails pickupDetails) {
        this.pickupDetails = pickupDetails;
    }
}