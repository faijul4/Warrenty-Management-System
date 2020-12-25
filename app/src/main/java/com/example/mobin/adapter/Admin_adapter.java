package com.example.mobin.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobin.R;
import com.example.mobin.Seller_Register;
import com.example.mobin.helper.Productutils;
import com.example.mobin.model.Seller_products_pojos;
import com.example.mobin.viewmodel.Product_viewmodel;

import java.util.List;

public class Admin_adapter extends RecyclerView.Adapter<Admin_adapter.ProductViewholder>  {
    private Context context;
    private List<Seller_products_pojos> productlist;
    private Product_viewmodel product_viewmodel=new Product_viewmodel();


    public Admin_adapter(Context context, List<Seller_products_pojos> productlist) {
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
    public void onBindViewHolder(@NonNull ProductViewholder holder, final int position) {
holder.SirialTV.setText(productlist.get(position).getSi_number());
holder.ModelTV.setText(productlist.get(position).getModel_number());
holder.WarrentyTV.setText(productlist.get(position).getWarrenty());
holder.CreatedateTv.setText(productlist.get(position).getCreateDate());
holder.rowTVmn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.admin_menu, popupMenu.getMenu());
        popupMenu.show();
        final Seller_products_pojos seller_products_pojos = productlist.get(position);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    case  R.id.delete_item:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Delete this entire product?");
                        builder.setMessage("Remember: Once Delete of the product it cannot be undone!");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                product_viewmodel.delete(seller_products_pojos);
                                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Cancel",null);

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();



                        break;
                }
                return false;
            }
        });


    }
});

        final String[] status = {String.valueOf((productlist.get(position).getStatus()))};
holder.REQbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, Seller_Register.class);
        context.startActivity(intent);
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
        private TextView SirialTV,ModelTV,WarrentyTV,CreatedateTv,BillTV,Warrenty_status,RemainingTV,rowTVmn;
        private Button REQbtn;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            SirialTV=itemView.findViewById(R.id.SirialTV_row);
            ModelTV=itemView.findViewById(R.id.ModelTV_row);
            WarrentyTV=itemView.findViewById(R.id.WarrentyTV_row);
            BillTV=itemView.findViewById(R.id.Billtv_row);
            RemainingTV=itemView.findViewById(R.id.statusWarrR);
            CreatedateTv=itemView.findViewById(R.id.SellingDAte_row);
            REQbtn=itemView.findViewById(R.id.changeBTN);
            rowTVmn=itemView.findViewById(R.id.row_menu);
        }
    }
}

