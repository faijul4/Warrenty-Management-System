package com.example.mobin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.model.Service;
import com.example.mobin.repos.Product_Repository;

import java.util.List;

public class Product_viewmodel extends ViewModel {
    private Product_Repository product_repository;
    public MutableLiveData<List<Seller_products_pojos>> productlistDB;
    public MutableLiveData<Seller_products_pojos> productdetailsLD=new MutableLiveData<>();

    public Product_viewmodel() {
        product_repository=new Product_Repository();
        productlistDB=product_repository.prodiuctlistDB;

    }
    public  void  save(Seller_products_pojos product){

        product_repository.addevent_to_db(product);
    }
}
