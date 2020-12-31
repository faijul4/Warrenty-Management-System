package com.example.mobin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mobin.adapter.Product_adapter;
import com.example.mobin.adapter.Service_adapter;
import com.example.mobin.model.Seller_products_pojos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Servicedashboard extends AppCompatActivity {
private RecyclerView ServiceRv;
private Service_adapter service_adapter;
private ArrayList<Seller_products_pojos> arrayList;
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference productref;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicedashboard);
        ServiceRv=findViewById(R.id.ServiceRV);
        arrayList = new ArrayList<>();
        ServiceRv.setLayoutManager(new LinearLayoutManager(this));
        service_adapter = new Service_adapter(this, arrayList);
        ServiceRv.setAdapter(service_adapter);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootref = FirebaseDatabase.getInstance().getReference();
        userref = rootref.child(firebaseUser.getUid());


        Query query = userref.child("product").orderByChild("status").equalTo(1);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    arrayList.clear();
                    for (DataSnapshot DSS : snapshot.getChildren()) {
                        final Seller_products_pojos seller_products_pojos = DSS.getValue(Seller_products_pojos.class);
                        arrayList.add(seller_products_pojos);

                    }
                    service_adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(Servicedashboard.this, "not found", Toast.LENGTH_SHORT).show();
                    arrayList.clear();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Servicedashboard.this, "no found", Toast.LENGTH_SHORT).show();
            }
        });


    }
}