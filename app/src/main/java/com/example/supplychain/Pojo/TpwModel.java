package com.example.supplychain.Pojo;
public class TpwModel
{
    private String awb;
    private String address;
    private String quantity;
    private String length;
    private String width;
    private String height;
    private String status;
    public TpwModel(String awb, String address, String quantity,String length,String width,String height, String status)
    {
        this.quantity = quantity;
        this.length = length;
        this.width = width;
        this.height = height;
        this.awb = awb;
        this.address = address;
        this.status = status;
    }

    public String getAwb() {return "AWB : "+awb;}
    public void setAwb(String awb) { this.awb = awb; }
    public String getAddress() {return address; }
    public void setAddress(String address) {this.address = address; }
    public String getQuantity() {return "Quantity : "+quantity;}
    public void setQuantity(String quantity) {this.quantity = quantity; }
    public String getStatus() {return "Status : "+status; }
    public void setStatus(String status) {this.status = status; }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
}