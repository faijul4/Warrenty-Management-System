package com.example.mobin.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class M_activity extends AppCompatActivity {
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference productref;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    ListView lvDiscussionTopics;
    ArrayList<String> listOfDiscussion = new ArrayList<String>();
    ArrayAdapter arrayAdpt;

    String UserName;

     DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().getRoot();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector);


        lvDiscussionTopics = (ListView) findViewById(R.id.lvDiscussionTopics);
        arrayAdpt = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listOfDiscussion);
        lvDiscussionTopics.setAdapter(arrayAdpt);


        getUserName();


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootref = FirebaseDatabase.getInstance().getReference();
        userref=rootref.child("product");


        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                arrayAdpt.clear();
                arrayAdpt.addAll(set);
                arrayAdpt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        lvDiscussionTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DiscussionActivity.class);
                i.putExtra("selected_topic", ((TextView)view).getText().toString());
                i.putExtra("user_name", UserName);
                startActivity(i);
            }
        });
    }
    private void getUserName(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText userName = new EditText(this);
        builder.setTitle("Enter your name");
        builder.setView(userName);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserName = userName.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getUserName();
            }
        });
        builder.show();
    }
}