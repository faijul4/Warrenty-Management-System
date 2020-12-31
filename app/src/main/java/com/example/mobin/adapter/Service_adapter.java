package com.example.mobin.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobin.R;
import com.example.mobin.helper.Productutils;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.viewmodel.Product_viewmodel;

import java.util.List;

public class Service_adapter extends RecyclerView.Adapter<Service_adapter.ProductViewholder>  {
    private Context context;
    private List<Seller_products_pojos> productlist;
    private Bundle bundle;
    private Product_viewmodel product_viewmodel;
    private String status;
    private String product_warrenty;
    private String product_bill;
    private String product_model;
    private String product_sirial;
    private String Createdate;
    private  String feedback;

    public Service_adapter(Context context, List<Seller_products_pojos> productlist) {
        this.context = context;
        this.productlist = productlist;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.service_row,parent,false);
        return new ProductViewholder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, final int position) {
holder.SirialTV.setText(productlist.get(position).getSi_number());
holder.ModelTV.setText(productlist.get(position).getModel_number());
holder.WarrentyTV.setText(productlist.get(position).getWarrenty());
holder.CreatedateTv.setText(productlist.get(position).getCreateDate());
holder.BillTV.setText(String.valueOf(productlist.get(position).getBill()));
        status = String.valueOf(productlist.get(position).getStatus());
        product_warrenty=productlist.get(position).getWarrenty();
        product_bill= String.valueOf(productlist.get(position).getBill());
        product_model=productlist.get(position).getModel_number();
        product_sirial=productlist.get(position).getSi_number();
        Createdate=productlist.get(position).getCreateDate();
        feedback=productlist.get(position).getFeedback();

holder.Warrenty_problem.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(feedback);
        AlertDialog alert = builder.create();
        alert.show();
    }
});
holder.confirmIMG.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final  String productid=productlist.get(position).getSeller_product_ID();
     /*   bundle=new Bundle();
        bundle.putString("id",productid);
        Intent intent=new Intent(context, Addstatus.class);
        intent.putExtras(bundle);
        context.startActivity(intent);*/
        product_viewmodel= ViewModelProviders.of((FragmentActivity) context).get(Product_viewmodel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText input=new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
       builder.setTitle("confirm your Service");
        builder.setView(input);
        builder.setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        status="0";
                        feedback=input.getText().toString();
                       Seller_products_pojos event=new Seller_products_pojos(productid,product_sirial,product_model,Integer.parseInt(product_bill),product_warrenty, Createdate,Integer.parseInt(status),feedback);
                       product_viewmodel.update(event);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
});
        String goingdate=(productlist.get(position).getWarrenty());
        long differdate= Productutils.getDefferentBetweenTwoDate(Productutils.getcurrentdate(),goingdate);
        if (differdate==0)
        {
            holder.RemainingTV.setText("Have a safe Journey!");

        }
        else if (differdate<0)
        {
            holder.RemainingTV.setText("Tour Finished!");
        }

        else
        {
            holder.RemainingTV.setText(String.valueOf(Productutils.getDefferentBetweenTwoDate(Productutils.getcurrentdate(),goingdate))+" Days Warrenty left");

        }

    }


    @Override
    public int getItemCount() {
        return productlist.size();
    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        private TextView SirialTV,ModelTV,WarrentyTV,CreatedateTv,BillTV,RemainingTV;
        private CardView Warrenty_problem;
        private ImageView confirmIMG;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            SirialTV=itemView.findViewById(R.id.SirialTV_row);
            ModelTV=itemView.findViewById(R.id.ModelTV_row);
            WarrentyTV=itemView.findViewById(R.id.WarrentyTV_row);
            BillTV=itemView.findViewById(R.id.Billtv_row);
            RemainingTV=itemView.findViewById(R.id.statusWarrR);
            CreatedateTv=itemView.findViewById(R.id.SellingDAte_row);
            Warrenty_problem=itemView.findViewById(R.id.problemCV);
            confirmIMG=itemView.findViewById(R.id.confirmIV);
        }
    }
}

