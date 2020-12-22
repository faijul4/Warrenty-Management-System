package com.example.mobin.model;

public class Seller_pojos {
    private  String SellerID;
    private String sellername;
    private String selleremail;
    private String password;

    public Seller_pojos() {
    }

    public Seller_pojos(String sellerID, String sellername,String selleremail, String password) {
        SellerID = sellerID;
        this.sellername = sellername;
        this.password = password;
        this.selleremail=selleremail;
    }

    public String getSelleremail() {
        return selleremail;
    }

    public void setSelleremail(String selleremail) {
        this.selleremail = selleremail;
    }

    public String getSellerID() {
        return SellerID;
    }

    public void setSellerID(String sellerID) {
        SellerID = sellerID;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
