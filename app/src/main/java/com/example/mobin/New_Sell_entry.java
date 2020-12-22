package com.example.mobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobin.helper.Productutils;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.viewmodel.Product_viewmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class New_Sell_entry extends AppCompatActivity {
private EditText  SirialET,ModelET,BillET,WarrentybTN;
private Button RegisterPRDCT;
private Product_viewmodel product_viewmodel;
private String warrentyDate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__sell_entry);

        SirialET=findViewById(R.id.SirialET);
        ModelET=findViewById(R.id.ModelET);
        BillET=findViewById(R.id.BillET);
        WarrentybTN=findViewById(R.id.WarrentyBTN);
        RegisterPRDCT=findViewById(R.id.SellEntryBTN);
        product_viewmodel= ViewModelProviders.of(New_Sell_entry.this).get(Product_viewmodel.class);
        RegisterPRDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_sirial=SirialET.getText().toString();
                String product_model=ModelET.getText().toString();
                String product_bill= (BillET.getText().toString());
                String product_warrenty=WarrentybTN.getText().toString();
                if (product_bill.isEmpty()&&product_model.isEmpty()&&product_warrenty.isEmpty()&&product_sirial.isEmpty()){
                    Toast.makeText(New_Sell_entry.this, "provide info", Toast.LENGTH_SHORT).show();
                }else {
                    Seller_products_pojos event=new Seller_products_pojos(null,product_sirial,product_model,Integer.parseInt(product_bill), product_warrenty,Productutils.getDateWithTime(),0);
                    product_viewmodel.save(event);


                }
            }
        });
        WarrentybTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datedialogshow();
            }
        });

    }

    private void datedialogshow() {
        Calendar calendar= Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(New_Sell_entry.this,datelistener,year,month,day);
        dpd.show();
    }
    private  DatePickerDialog.OnDateSetListener datelistener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar calendar=Calendar.getInstance();
            calendar.set(i,i1,i2);
            warrentyDate=new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
            WarrentybTN.setText(warrentyDate);
        }
    };
    }

