package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceLog extends AppCompatActivity {
    private EditText usernameET,passwordET;
    private Button Logged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_log);
        usernameET=findViewById(R.id.Service_username_log);
        passwordET=findViewById(R.id.Service_pass_Log);
        Logged=findViewById(R.id.Service_LoginBTN);
        Logged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameET.getText().toString().equals("mobin") && passwordET.getText().toString().equals("23") ){
                    Intent intent=new Intent(ServiceLog.this,Service_dashboard.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(ServiceLog.this, "sorry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}