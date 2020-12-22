package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobin.viewmodel.Login_Viewmodel;

public class Seller_Login extends AppCompatActivity {
private TextView Register;
private EditText emailLOgin,passwordlogin;
private Button logBTN;
private Login_Viewmodel login_viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__login);
        Register=findViewById(R.id.SellerRGstrLOG);
        emailLOgin=findViewById(R.id.Seller_email_log);
        passwordlogin=findViewById(R.id.Seller_pass_Log);
        logBTN=findViewById(R.id.Seller_LoginBTN);
        login_viewmodel= ViewModelProviders.of(Seller_Login.this).get(Login_Viewmodel.class);
         logBTN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String Seller_email=emailLOgin.getText().toString();
                 String Seller_password=passwordlogin.getText().toString();
                 if (Seller_email.isEmpty() && Seller_password.isEmpty()){
                     Toast.makeText(Seller_Login.this, "provide info", Toast.LENGTH_SHORT).show();
                 }else {
login_viewmodel.login(Seller_email,Seller_password);
                     Intent intent=new Intent(Seller_Login.this,Seller_dashboard.class);
                     startActivity(intent);
                 }
             }
         });
        login_viewmodel.statelivedata.observe(this, new Observer<Login_Viewmodel.Authenticationstate>() {
            @Override
            public void onChanged(Login_Viewmodel.Authenticationstate authenticationstate) {
                switch (authenticationstate){
                    case AUTHENTICATIONSTATED:

                        break;
                    case UNAUTHENTICATIONSTATED:

                        break;
                }
            }
        });
    }
}