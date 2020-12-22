package com.example.mobin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobin.model.Seller_pojos;
import com.example.mobin.model.Service_pojos;
import com.example.mobin.repos.Firebase_repository;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Viewmodel extends ViewModel {
    public enum Authenticationstate{
        AUTHENTICATIONSTATED,
        UNAUTHENTICATIONSTATED

    }
    private Firebase_repository repository;
    public MutableLiveData<Authenticationstate> statelivedata;
    public  MutableLiveData<Seller_pojos>userinfold=new MutableLiveData<>();
    public  MutableLiveData<String>errmsg=new MutableLiveData<>();


    public Login_Viewmodel(){
        statelivedata=new MutableLiveData<>();
        repository=new Firebase_repository(statelivedata);
        errmsg=repository.getErrmsg();
        if (repository.getFirebaseUser()!=null){
            statelivedata.postValue(Authenticationstate.AUTHENTICATIONSTATED);

        }else {
            statelivedata.postValue(Authenticationstate.UNAUTHENTICATIONSTATED);

        }


    }

    public  void  register(Seller_pojos userInformationPojo){

        statelivedata=repository.registrationuser(userInformationPojo);
    }
    public  void  Service_register(Service_pojos service_pojos){

        statelivedata=repository.Service_registrationuser(service_pojos);
    }
    public  void  getuserinfo(){

        userinfold=repository.getuserinfofromDB();
    }
    public  void  login(String email,String password){

        statelivedata=repository.loginfirebaseuser(email, password);
    }

    public void  logout(){
        FirebaseAuth.getInstance().signOut();
        statelivedata.postValue(Authenticationstate.UNAUTHENTICATIONSTATED);

    }


}
