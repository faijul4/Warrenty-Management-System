package com.example.mobin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobin.R;
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
        View itemview=inflater.inflate(R.layout.product_row,parent,false);
        return new ProductViewholder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {
holder.SirialTV.setText(productlist.get(position).getSi_number());
holder.ModelTV.setText(productlist.get(position).getModel_number());
holder.WarrentyTV.setText(productlist.get(position).getWarrenty());
holder.CreatedateTv.setText(productlist.get(position).getCreateDate());
holder.BillTV.setText(String.valueOf(productlist.get(position).getBill()));

    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        private TextView SirialTV,ModelTV,WarrentyTV,CreatedateTv,BillTV,Warrenty_status;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            SirialTV=itemView.findViewById(R.id.SirialTV_row);
            ModelTV=itemView.findViewById(R.id.ModelTV_row);
            WarrentyTV=itemView.findViewById(R.id.WarrentyTV_row);
            CreatedateTv=itemView.findViewById(R.id.SellingDAte_row);
            BillTV=itemView.findViewById(R.id.Billtv_row);
            Warrenty_status=itemView.findViewById(R.id.statusWR);
        }
    }
}

