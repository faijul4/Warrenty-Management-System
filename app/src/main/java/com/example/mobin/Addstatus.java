/*
package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobin.helper.Productutils;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.viewmodel.Product_viewmodel;

public class Addstatus extends AppCompatActivity {
private EditText STSET;
private Button stsBTN;
private Product_viewmodel product_viewmodel;


private String updateproductID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstatus);

        Intent i=getIntent();
        final String title=i.getStringExtra("id");
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        STSET=findViewById(R.id.stsET);
        product_viewmodel= ViewModelProviders.of(Addstatus.this).get(Product_viewmodel.class);

        stsBTN=findViewById(R.id.stsBTN);
        product_viewmodel.getProductdetails(title);

        product_viewmodel.productdetailsLD.observe(this, new Observer<Seller_products_pojos>() {
            @Override
            public void onChanged(Seller_products_pojos seller_products_pojos) {
             try {
                 STSET.setText(String.valueOf(seller_products_pojos.getStatus()));



                 Toast.makeText(Addstatus.this, seller_products_pojos.getStatus(), Toast.LENGTH_SHORT).show();
             }catch (Exception e){
//                 Toast.makeText(Addstatus.this, (CharSequence) e, Toast.LENGTH_SHORT).show();
             }

            }
        });
        stsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Stts=Status;


            }
        });
    }
}*/
