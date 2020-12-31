package com.example.mobin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobin.R;
import com.example.mobin.helper.Productutils;
import com.example.mobin.model.Seller_products_pojos;

import java.util.List;

public class Product_adapter  extends RecyclerView.Adapter<Product_adapter.ProductViewholder>  {
    private Context context;
    private List<Seller_products_pojos> productlist;

    public Product_adapter(Context context, List<Seller_products_pojos> productlist) {
        this.context = context;
        this.productlist = productlist;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.admin_row,parent,false);
        return new ProductViewholder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {
holder.SirialTV.setText(productlist.get(position).getSi_number());
holder.ModelTV.setText(productlist.get(position).getModel_number());
holder.WarrentyTV.setText(productlist.get(position).getWarrenty());
holder.CreatedateTv.setText(productlist.get(position).getCreateDate());
holder.BillTV.setText(String.valueOf(productlist.get(position).getBill()));
        String goingdate=(productlist.get(position).getWarrenty());
        long differdate= Productutils.getDefferentBetweenTwoDate(Productutils.getcurrentdate(),goingdate);
        if (differdate==0)
        {
            holder.RemainingTV.setText("Service timeout");

        }
        else if (differdate<0)
        {
            holder.RemainingTV.setText("no service");
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
        private TextView SirialTV,ModelTV,WarrentyTV,CreatedateTv,BillTV,Warrenty_status,RemainingTV;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            SirialTV=itemView.findViewById(R.id.SirialTV_row);
            ModelTV=itemView.findViewById(R.id.ModelTV_row);
            WarrentyTV=itemView.findViewById(R.id.WarrentyTV_row);
            BillTV=itemView.findViewById(R.id.Billtv_row);
            RemainingTV=itemView.findViewById(R.id.statusWarrR);
            CreatedateTv=itemView.findViewById(R.id.SellingDAte_row);
        }
    }
}

