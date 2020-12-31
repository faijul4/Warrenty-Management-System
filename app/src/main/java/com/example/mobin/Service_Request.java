package com.example.mobin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobin.adapter.Product_adapter;
import com.example.mobin.adapter.ServiceReq_adapter;
import com.example.mobin.model.Seller_pojos;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.model.Service_pojos;
import com.example.mobin.test.M_activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class Service_Request extends AppCompatActivity {
    private EditText RequestET;
    private CardView chatBTN,CheckBTN;
    private RecyclerView recyclerView;
    private ServiceReq_adapter serviceReq_adapter;
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference productref;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private Seller_pojos seller_pojos;
    private ArrayList<Seller_products_pojos> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__request);
        RequestET = findViewById(R.id.ServiceREquestET);
        CheckBTN = findViewById(R.id.CheckBTN);
        recyclerView = findViewById(R.id.check_ReqRV);
        chatBTN = findViewById(R.id.chatBTN);
        arrayList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        serviceReq_adapter = new ServiceReq_adapter(this, arrayList);
        recyclerView.setAdapter(serviceReq_adapter);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootref = FirebaseDatabase.getInstance().getReference();
        userref = rootref.child(firebaseUser.getUid());
chatBTN.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Service_Request.this, M_activity.class);
        startActivity(intent);
    }
});
        CheckBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtext = RequestET.getText().toString();
                if (!searchtext.isEmpty()) {
                    Firebasesearch(searchtext);
                }else {
                    Toast.makeText(Service_Request.this, "empty", Toast.LENGTH_SHORT).show();
                }
            }

            private void Firebasesearch(String s) {
                Query query = userref.child("product").orderByChild("si_number")
                        .equalTo(s);


                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            arrayList.clear();
                            for (DataSnapshot DSS : snapshot.getChildren()) {
                                final Seller_products_pojos seller_products_pojos = DSS.getValue(Seller_products_pojos.class);
                                arrayList.add(seller_products_pojos);

                            }
                            serviceReq_adapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(Service_Request.this, "not found", Toast.LENGTH_SHORT).show();
                            arrayList.clear();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Service_Request.this, "no found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}