package com.example.supplychain.Request;
import com.google.gson.annotations.SerializedName;
public class LoginRequest {
    @SerializedName("email")
    String email;
    @SerializedName("passcode")
    String passcode;
    @SerializedName("dcOrg")
    String dcOrg;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasscode() {
        return passcode;
    }
    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
    public String getDcOrg() {
        return dcOrg;
    }
    public void setDcOrg(String dcOrg) {
        this.dcOrg = dcOrg;
    }
}

