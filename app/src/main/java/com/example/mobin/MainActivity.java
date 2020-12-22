package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private CardView SellerloginCV,ServiceCV,ServiceREquestCV,adminCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SellerloginCV=findViewById(R.id.sellerCV);
        ServiceCV=findViewById(R.id.ServiceCV);
        adminCV=findViewById(R.id.adminCV);

        ServiceREquestCV=findViewById(R.id.RequestCV);
        adminCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Admin_log.class);
                startActivity(intent);
            }
        });
        SellerloginCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Seller_Login.class);
                startActivity(intent);
            }
        });
        ServiceCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ServiceLog.class);
                startActivity(intent);
            }
        });
        ServiceREquestCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Service_Request.class);
                startActivity(intent);
            }
        });
    }
}