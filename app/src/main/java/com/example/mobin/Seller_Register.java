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

import com.example.mobin.model.Seller_pojos;
import com.example.mobin.viewmodel.Login_Viewmodel;

public class Seller_Register extends AppCompatActivity {
    private EditText Seller_username, Seller_password, Seller_confirmpass, Seller_email;
    private TextView showError,ServiceTV;
    private Button Seller_Register;
    private Login_Viewmodel login_viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__register);
        Seller_username = findViewById(R.id.seller_nameRegET);
        Seller_password = findViewById(R.id.Seller_pass_regET);
        Seller_confirmpass = findViewById(R.id.seller_confirmpassET);
        Seller_email = findViewById(R.id.Seller_emailRegET);
        Seller_Register= findViewById(R.id.Seller_REgBTN);
        showError = findViewById(R.id.errorTV);
        login_viewmodel = ViewModelProviders.of(Seller_Register.this).get(Login_Viewmodel.class);

        Seller_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Seller_username.getText().toString();
                String password = Seller_password.getText().toString();
                String confirmpass = Seller_confirmpass.getText().toString();
                String email = Seller_email.getText().toString();
                if (username.isEmpty() && password.isEmpty() && confirmpass.isEmpty() && email.isEmpty()) {
                    Toast.makeText(Seller_Register.this, "fill first", Toast.LENGTH_SHORT).show();

                } else {
                    if (password.equals(confirmpass)) {
                        Seller_pojos userInformationPojo = new Seller_pojos(null, username, email, password);
                        login_viewmodel.register(userInformationPojo);
                        Intent intent=new Intent(Seller_Register.this,MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(Seller_Register.this, "done", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Seller_Register.this, "not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
      /*  login_viewmodel.statelivedata.observe(this, new Observer<Login_Viewmodel.Authenticationstate>() {
            @Override
            public void onChanged(Login_Viewmodel.Authenticationstate authenticationstate) {
                switch (authenticationstate) {
                    case AUTHENTICATIONSTATED:
                        Toast.makeText(Seller_Register.this, "huray", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Seller_Register.this,New_Sell_entry.class);
                        startActivity(intent);
                        break;
                    case UNAUTHENTICATIONSTATED:
                        Toast.makeText(Seller_Register.this, "nohuray", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });*/
        login_viewmodel.errmsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError.setText(s);
            }
        });

    }
}