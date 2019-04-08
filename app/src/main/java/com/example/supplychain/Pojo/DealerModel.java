package com.example.supplychain.Pojo;
public class DealerModel
{
    private String po;
    private String line;
    private String shipno;
    private String awb;
    private String item;
    private int quantity;
    private String needby;
    private String shipto;
    private String status;
    public DealerModel(String po, String line, String shipno, String awb, String item, int quantity, String needby, String shipto, String status)
    {
        this.po = po;
        this.line = line;
        this.shipno = shipno;
        this.awb = awb;
        this.item = item;
        this.quantity = quantity;
        this.needby = needby;
        this.shipto = shipto;
        this.status = status;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getShipno() {
        return shipno;
    }

    public void setShipno(String shipno) {
        this.shipno = shipno;
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNeedby() {
        return needby;
    }

    public void setNeedby(String needby) {
        this.needby = needby;
    }

    public String getShipto() {
        return shipto;
    }

    public void setShipto(String shipto) {
        this.shipto = shipto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
