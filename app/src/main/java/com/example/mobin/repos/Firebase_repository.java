package com.example.mobin.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobin.model.Seller_pojos;
import com.example.mobin.model.Service_pojos;
import com.example.mobin.viewmodel.Login_Viewmodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase_repository {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    DatabaseReference rootref;
    DatabaseReference userref;
    DatabaseReference userinfo;
    public  MutableLiveData<Login_Viewmodel.Authenticationstate>statelivedata=new MutableLiveData<>();
    public  MutableLiveData<Seller_pojos>userinfold=new MutableLiveData<>();
    public  MutableLiveData<String>errmsg=new MutableLiveData<>();
    public  Firebase_repository(MutableLiveData<Login_Viewmodel.Authenticationstate> statelivedata){

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        this.statelivedata=statelivedata;

    }
    public  FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }
    public MutableLiveData<String> getErrmsg() {
        return errmsg;
    }
    public  MutableLiveData<Login_Viewmodel.Authenticationstate>  registrationuser(final Seller_pojos userInformationPojo){

        firebaseAuth.createUserWithEmailAndPassword(userInformationPojo.getSelleremail(), userInformationPojo.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    firebaseUser=firebaseAuth.getCurrentUser();
                    statelivedata.postValue(Login_Viewmodel.Authenticationstate.AUTHENTICATIONSTATED);
                    rootref= FirebaseDatabase.getInstance().getReference();
                    userref=rootref.child(firebaseUser.getUid());
                    userinfo=userref.child("Loginfo");

                    String userid=firebaseUser.getUid();
                    userInformationPojo.setSellerID(userid);

                    userinfo.setValue(userInformationPojo).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                        }
                    });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errmsg.postValue(e.getLocalizedMessage());
                statelivedata.postValue(Login_Viewmodel.Authenticationstate.UNAUTHENTICATIONSTATED);
            }
        });

        return statelivedata;
    }
 /*   public  MutableLiveData<Login_Viewmodel.Authenticationstate>  Service_registrationuser(final Service_pojos userInformationPojo){

        firebaseAuth.createUserWithEmailAndPassword(userInformationPojo.getSelleremail(), userInformationPojo.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    firebaseUser=firebaseAuth.getCurrentUser();
                    statelivedata.postValue(Login_Viewmodel.Authenticationstate.AUTHENTICATIONSTATED);
                    rootref= FirebaseDatabase.getInstance().getReference();
                    userref=rootref.child(firebaseUser.getUid());
                    userinfo=userref.child("Service_Loginfo");

                    String userid=firebaseUser.getUid();
                    userInformationPojo.setSellerID(userid);

                    userinfo.setValue(userInformationPojo).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                        }
                    });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errmsg.postValue(e.getLocalizedMessage());
                statelivedata.postValue(Login_Viewmodel.Authenticationstate.UNAUTHENTICATIONSTATED);
            }
        });

        return statelivedata;
    }*/
    public  MutableLiveData<Login_Viewmodel.Authenticationstate>  loginfirebaseuser(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    firebaseUser=firebaseAuth.getCurrentUser();

                   // statelivedata.postValue(Login_Viewmodel.Authenticationstate.AUTHENTICATIONSTATED);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errmsg.postValue(e.getLocalizedMessage());
                statelivedata.postValue(Login_Viewmodel.Authenticationstate.UNAUTHENTICATIONSTATED);
            }
        });
        return statelivedata;

    }
    public  MutableLiveData<Seller_pojos> getuserinfofromDB(){
        rootref=FirebaseDatabase.getInstance().getReference();
        userref=rootref.child(firebaseUser.getUid());
        userinfo=userref.child("Loginfo");
        userinfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Seller_pojos userInformationPojo=dataSnapshot.getValue(Seller_pojos.class);
                userinfold.postValue(userInformationPojo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return userinfold;
    }



}
