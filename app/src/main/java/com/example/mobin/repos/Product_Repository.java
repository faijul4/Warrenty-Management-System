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
        rootref= FirebaseDatabase.getInstance().getReference();
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
    public  void updatefromdb(Seller_products_pojos seller_products_pojos){
        String product_id=seller_products_pojos.getSeller_product_ID();
        seller_products_pojos.setSeller_product_ID(product_id);
        productref.child(product_id).setValue(seller_products_pojos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

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
    public  MutableLiveData<Seller_products_pojos>  getproductsbyeventid(final String productid){
        productref.child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Seller_products_pojos event=dataSnapshot.getValue(Seller_products_pojos.class);
                productdetailsLD.postValue(event);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });return productdetailsLD;
    }

    public  void  deleteeventfromdb(Seller_products_pojos seller_products_pojos){
        final String product_id=seller_products_pojos.getSeller_product_ID();
        productref.child(product_id).removeValue();


        }
    }

