package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Service_dashboard extends AppCompatActivity {
private CardView EntryCV,ServiceLOGCV,ExitCV,LogoutCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dashboard);
        EntryCV=findViewById(R.id.servicEntryCV);
        ServiceLOGCV=findViewById(R.id.ServiceLOGCV);
        ExitCV=findViewById(R.id.exitCV);
        LogoutCV=findViewById(R.id.logoutCV);
        EntryCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Service_dashboard.this,Seller_Register.class);
                startActivity(intent);
            }
        });

    }
}