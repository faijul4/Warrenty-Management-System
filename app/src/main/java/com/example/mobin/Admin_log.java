package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_log extends AppCompatActivity {
private EditText usernameET,passwordET;
private Button Logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log);
        usernameET=findViewById(R.id.admin_username_log);
        passwordET=findViewById(R.id.admin_pass_Log);
        Logged=findViewById(R.id.admin_LoginBTN);
        Logged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (usernameET.getText().toString().equals("mobin") && passwordET.getText().toString().equals("23") ){
                    Intent intent=new Intent(Admin_log.this,Seller_Register.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Admin_log.this, "sorry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}