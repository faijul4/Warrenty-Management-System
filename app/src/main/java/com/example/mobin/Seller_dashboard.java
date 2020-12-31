package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.mobin.adapter.Product_adapter;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.viewmodel.Login_Viewmodel;
import com.example.mobin.viewmodel.Product_viewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Seller_dashboard extends AppCompatActivity {
private RecyclerView recyclerView;
private FloatingActionButton register_product;
private Product_adapter product_adapter;
private Login_Viewmodel login_viewmodel;
private Product_viewmodel product_viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        recyclerView=findViewById(R.id.Soldlist);
        register_product=findViewById(R.id.Register_productFBTN);

        product_viewmodel= ViewModelProviders.of(Seller_dashboard.this).get(Product_viewmodel.class);
        login_viewmodel= ViewModelProviders.of(Seller_dashboard.this).get(Login_Viewmodel.class);
        register_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seller_dashboard.this,New_Sell_entry.class);
                startActivity(intent);
            }
        });product_viewmodel.productlistDB.observe(this,
                new Observer<List<Seller_products_pojos>>() {
            @Override
            public void onChanged(List<Seller_products_pojos> seller_products_pojos) {
                product_adapter = new Product_adapter(Seller_dashboard.this, seller_products_pojos);
                LinearLayoutManager llm = new LinearLayoutManager(Seller_dashboard.this);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(product_adapter);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logoutMN:
                login_viewmodel.logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}