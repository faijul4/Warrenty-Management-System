package com.example.mobin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobin.R;
import com.example.mobin.Service_Request;
import com.example.mobin.adapter.chat.Useradapter;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFrag extends Fragment {
    private RecyclerView recyclerView;
    private Useradapter useradapter;
    private List<Users>usersList;
    private String userid;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View  view=inflater.inflate(R.layout.fragment_user, container, false);
recyclerView=view.findViewById(R.id.recycleruser);
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usersList=new ArrayList<>();
        usersList.add(new Users(userid,"anonynimous"));

useradapter=new Useradapter(getContext(),usersList);
recyclerView.setAdapter(useradapter);

readuser();
return view;
    }

    private void readuser() {
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    usersList.clear();
                    for (DataSnapshot DSS : snapshot.getChildren()) {
                        final Users users = DSS.getValue(Users.class);
                        usersList.add(users);

                    }
                    useradapter.notifyDataSetChanged();
                }else {
                    usersList.clear();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}