package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash_activity extends AppCompatActivity {
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                doWOrk();
                StartApp();
            }

        });
        thread.start();
    }

    private void StartApp() {
        Intent intent=new Intent(Splash_activity.this,MainActivity.class);
        startActivity(intent);

    }

    private void doWOrk() {
        for (i=1;i<=2;i=i+1) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
