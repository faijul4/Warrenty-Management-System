package com.example.mobin.repos;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobin.model.Seller_products_pojos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Product_Repository {
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference productref;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<Seller_products_pojos>> prodiuctlistDB;
    public MutableLiveData<Seller_products_pojos> productdetailsLD=new MutableLiveData<>();

    public Product_Repository() {
        prodiuctlistDB = new MutableLiveData<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootref = FirebaseDatabase.getInstance().getReference();
        userref = rootref.child(firebaseUser.getUid());
        productref = userref.child("product");
        productref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Seller_products_pojos> product_list = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    product_list.add(d.getValue(Seller_products_pojos.class));

                }
                prodiuctlistDB.postValue(product_list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        public  void  addevent_to_db(Seller_products_pojos product ){
            String productID=productref.push().getKey();
            product.setSeller_product_ID(productID);
            productref.child(productID).setValue(product).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

    }

