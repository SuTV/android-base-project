package com.planday.biz.model;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("idCard")
    private String idCard;

    @SerializedName("email")
    private String email;

    public Info() {
    }

    public Info(String idCard, String email) {
        this.idCard = idCard;
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
