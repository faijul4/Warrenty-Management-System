package com.example.mobin.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mobin.R;
import com.example.mobin.adapter.chat.Messagedapter;
import com.example.mobin.model.Chats;
import com.example.mobin.model.Seller_pojos;
import com.example.mobin.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MessageActivity2 extends AppCompatActivity {
    private TextView usernameTV;
    private DatabaseReference databaseReference;
    private Intent intent;
    private RecyclerView recyclerView;
    private Messagedapter messagedapter;
    private List<Chats> mchats;
    private EditText messageEt;
    private ImageButton sendmesssage;
    private List<Seller_pojos> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message2);
        usernameTV = findViewById(R.id.toolbaruser);
        recyclerView = findViewById(R.id.recyclerchat);
        messageEt = findViewById(R.id.textsend);
        sendmesssage = findViewById(R.id.send_btn);
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

      /*  Toolbar toolbar=findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);*/
        intent = getIntent();
        final String username = intent.getStringExtra("userid");
        final String userid = intent.getStringExtra("userid");
        sendmesssage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssg = messageEt.getText().toString();
                if (!mssg.equals("")) {
                    sendmesssssage("message", username, mssg);
                } else {
                    Toast.makeText(MessageActivity2.this, "sorry", Toast.LENGTH_SHORT).show();
                }
                messageEt.setText("");
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child(username);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot DSS : snapshot.getChildren()) {

                        final Users users = DSS.getValue(Users.class);
                        usernameTV.setText(users.getUsername());

                    }

                } else {

                }
                Readmessage("message","mine");
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sendmesssssage(String Sender, String Reciever, String Message) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("sender", Sender);
        hashMap.put("reciever", Reciever);
        hashMap.put("Message", Message);
        databaseReference.child("chats").push().setValue(hashMap);

    }

    private void Readmessage(final String myid, final String userid) {
        mchats = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mchats.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot DSS : snapshot.getChildren()) {
                        final Chats chats = DSS.getValue(Chats.class);
                        if (chats.getReciever().equals(myid) && chats.getSENDER().equals(userid) || chats.getReciever().equals(userid) && chats.getSENDER().equals(myid)) {
                            mchats.add(chats);
                        }

                        Messagedapter messagedapter=new Messagedapter(MessageActivity2.this,mchats);
                        recyclerView.setAdapter(messagedapter);
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}