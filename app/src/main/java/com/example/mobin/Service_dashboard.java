package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mobin.test.M_activity;

public class Service_dashboard extends AppCompatActivity {
private CardView EntryCV,ServiceLOGCV,ExitCV,LogoutCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dashboard);
        EntryCV=findViewById(R.id.servicEntryCV);

        ExitCV=findViewById(R.id.exitCV);
        EntryCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Service_dashboard.this,Servicedashboard.class);
                startActivity(intent);
            }
        });
      ExitCV.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(Service_dashboard.this, M_activity.class);
              startActivity(intent);
          }
      });

    }
}