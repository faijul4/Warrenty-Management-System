package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobin.adapter.Admin_adapter;
import com.example.mobin.adapter.Product_adapter;
import com.example.mobin.model.Seller_products_pojos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Admin_dashboard extends AppCompatActivity {
private RecyclerView recyclerView;
private Admin_adapter admin_adapter;
    private ArrayList<Seller_products_pojos> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        recyclerView=findViewById(R.id.AdminRV);
        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        admin_adapter = new Admin_adapter(this, arrayList);
        recyclerView.setAdapter(admin_adapter);


    }
}