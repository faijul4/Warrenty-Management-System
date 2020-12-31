package com.example.mobin.adapter.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobin.R;
import com.example.mobin.fragments.MessageActivity2;
import com.example.mobin.model.Chats;
import com.example.mobin.model.Users;

import java.util.List;

public class Messagedapter extends RecyclerView.Adapter<Messagedapter.MessageViewholder> {
    private  static  final int MSG_TYPE_LEFT=0;
    private  static  final int MSG_TYPE_RIGHT=1;
private Context context;
private List<Chats>mchats;

    public Messagedapter(Context context, List<Chats> mchats) {
        this.context = context;
        this.mchats=mchats;
    }

    @NonNull
    @Override
    public MessageViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==MSG_TYPE_RIGHT){
            LayoutInflater inflater=LayoutInflater.from(context);
            View itemview=inflater.inflate(R.layout.chat_right,parent,false);
            return new Messagedapter.MessageViewholder(itemview);
        }else {
            LayoutInflater inflater=LayoutInflater.from(context);
            View itemview=inflater.inflate(R.layout.chat_left,parent,false);
            return new Messagedapter.MessageViewholder(itemview);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewholder holder, final int position) {
    Chats chats=mchats.get(position);
    holder.username.setText(chats.getMessage());
        Toast.makeText(context, mchats.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
       return mchats.size();
    }

    class MessageViewholder extends RecyclerView.ViewHolder{
private TextView username;
        public MessageViewholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.usertv_row);
        }
    }

    @Override
    public int getItemViewType(int position) {

            return MSG_TYPE_LEFT;


    }
}
