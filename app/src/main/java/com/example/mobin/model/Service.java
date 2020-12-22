package com.example.mobin.model;

public class Service {
    private String Service_ID;
    private Seller_products_pojos seller_products_pojos;
    private Seller_pojos seller_pojos;
    private Service_pojos service_pojos;

    public Service() {
    }

    public Service(String service_ID, Seller_products_pojos seller_products_pojos, Seller_pojos seller_pojos, Service_pojos service_pojos) {
        Service_ID = service_ID;
        this.seller_products_pojos = seller_products_pojos;
        this.seller_pojos = seller_pojos;
        this.service_pojos = service_pojos;
    }

    public String getService_ID() {
        return Service_ID;
    }

    public void setService_ID(String service_ID) {
        Service_ID = service_ID;
    }

    public Seller_products_pojos getSeller_products_pojos() {
        return seller_products_pojos;
    }

    public void setSeller_products_pojos(Seller_products_pojos seller_products_pojos) {
        this.seller_products_pojos = seller_products_pojos;
    }

    public Seller_pojos getSeller_pojos() {
        return seller_pojos;
    }

    public void setSeller_pojos(Seller_pojos seller_pojos) {
        this.seller_pojos = seller_pojos;
    }

    public Service_pojos getService_pojos() {
        return service_pojos;
    }

    public void setService_pojos(Service_pojos service_pojos) {
        this.service_pojos = service_pojos;
    }
}
