
package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {
    private DatabaseReference myDatabase;
    private DatabaseReference chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        myDatabase = FirebaseDatabase.getInstance().getReference();
        chat=myDatabase.child("chats");

        final TextView myText = findViewById(R.id.textbox);
        chat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myText.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myText.setText("CANCELLED");

            }
        });
    }

    public void sendMessage(View view){
        EditText myEditText = findViewById(R.id.editText);

        myDatabase.child("anonymous").setValue(myEditText);
        myEditText.setText("");
    }
}

