
package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobin.fragments.Chatfrag;
import com.example.mobin.fragments.UserFrag;
import com.example.mobin.adapter.Viewpager_adapter;
import com.example.mobin.viewmodel.Product_viewmodel;
import com.google.android.material.tabs.TabLayout;

public class Addstatus extends AppCompatActivity {
private EditText STSET;
private Button stsBTN;
private Product_viewmodel product_viewmodel;



private String updateproductID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstatus);

        Intent i=getIntent();
        final String title=i.getStringExtra("id");
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        TabLayout tabLayout=findViewById(R.id.tablayout);
        ViewPager viewPager=findViewById(R.id.viewpager);
        product_viewmodel= ViewModelProviders.of(Addstatus.this).get(Product_viewmodel.class);
        Viewpager_adapter viewPager1=new Viewpager_adapter(getSupportFragmentManager());
         viewPager1.addFragment(new Chatfrag(),"masum");
         viewPager1.addFragment(new UserFrag(),"mobin");

         viewPager.setAdapter(viewPager1);

         tabLayout.setupWithViewPager(viewPager);




    }

}
