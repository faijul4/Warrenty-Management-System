package com.example.mobin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobin.model.Seller_pojos;
import com.example.mobin.model.Service_pojos;
import com.example.mobin.viewmodel.Login_Viewmodel;

public class Service_Register extends AppCompatActivity {
    private EditText Seller_username, Seller_password, Seller_confirmpass, Seller_email;
    private TextView showError;
    private Button Register;
    private Login_Viewmodel login_viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__register);
        Seller_username = findViewById(R.id.service_nameRegET);
        Seller_password = findViewById(R.id.Service_pass_regET);
        Seller_confirmpass = findViewById(R.id.service_confirmpassET);
        Seller_email = findViewById(R.id.Service_emailRegET);
        Register = findViewById(R.id.Service_REgBTN);
        showError = findViewById(R.id.errorTV);
        login_viewmodel = ViewModelProviders.of(Service_Register.this).get(Login_Viewmodel.class);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Seller_username.getText().toString();
                String password = Seller_password.getText().toString();
                String confirmpass = Seller_confirmpass.getText().toString();
                String email = Seller_email.getText().toString();
                if (username.isEmpty() && password.isEmpty() && confirmpass.isEmpty() && email.isEmpty()) {
                    Toast.makeText(Service_Register.this, "fill first", Toast.LENGTH_SHORT).show();

                } else {
                    if (password.equals(confirmpass)) {
                        Service_pojos userInformationPojo = new Service_pojos(null, username, email, password);
                        login_viewmodel.Service_register(userInformationPojo);
                        Toast.makeText(Service_Register.this, "done", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Service_Register.this, "not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        login_viewmodel.statelivedata.observe(this, new Observer<Login_Viewmodel.Authenticationstate>() {
            @Override
            public void onChanged(Login_Viewmodel.Authenticationstate authenticationstate) {
                switch (authenticationstate) {
                    case AUTHENTICATIONSTATED:
                        Toast.makeText(Service_Register.this, "huray", Toast.LENGTH_SHORT).show();
                        break;
                    case UNAUTHENTICATIONSTATED:
                        Toast.makeText(Service_Register.this, "nohuray", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });
        login_viewmodel.errmsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError.setText(s);
            }
        });

    }
}