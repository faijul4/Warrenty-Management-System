package com.example.mobin.model;

public class Seller_products_pojos {
    private String Seller_product_ID;
    private String  Si_number;
    private  String Model_number;
    private int bill;
    private String warrenty;
    private String createDate;
    private int status;
    public Seller_products_pojos() {
    }

    public Seller_products_pojos(String seller_product_ID, String si_number, String model_number, int bill, String warrenty, String createDate,int status) {
        Seller_product_ID = seller_product_ID;
        Si_number = si_number;
        Model_number = model_number;
        this.bill = bill;
        this.status=status;
        this.warrenty = warrenty;
        this.createDate = createDate;
    }

    public String getSeller_product_ID() {
        return Seller_product_ID;
    }

    public void setSeller_product_ID(String seller_product_ID) {
        Seller_product_ID = seller_product_ID;
    }

    public int getStatus() {
        if (status==0){

        }
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSi_number() {
        return Si_number;
    }

    public void setSi_number(String si_number) {
        Si_number = si_number;
    }

    public String getModel_number() {
        return Model_number;
    }

    public void setModel_number(String model_number) {
        Model_number = model_number;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public String getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(String warrenty) {
        this.warrenty = warrenty;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
