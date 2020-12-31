package com.example.mobin.adapter.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobin.Admin_log;
import com.example.mobin.MainActivity;
import com.example.mobin.R;
import com.example.mobin.adapter.Product_adapter;
import com.example.mobin.fragments.MessageActivity2;
import com.example.mobin.model.Users;

import java.util.List;

public class Useradapter extends RecyclerView.Adapter<Useradapter.UserViewholder> {
private Context context;
private List<Users>musers;

    public Useradapter(Context context, List<Users> musers) {
        this.context = context;
        this.musers = musers;
    }

    @NonNull
    @Override
    public UserViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.product_row,parent,false);
        return new Useradapter.UserViewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewholder holder, final int position) {
        final Users users=musers.get(position);
holder.username.setText(users.getUsername());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(context, MessageActivity2.class);
       intent.putExtra("userid",users.getUsername());
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return musers.size();
    }

    class UserViewholder extends RecyclerView.ViewHolder{
private TextView username;
        public UserViewholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.usertv_row);
        }
    }
}
