package com.example.messengerapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRecyclerView extends RecyclerView.Adapter<ChatRecyclerView.MyViewHolder> {
    Context context;
    ArrayList<ChatListItem> chatListItems;
    public ChatRecyclerView(Context context, ArrayList<ChatListItem> chatListItem){
        this.context = context;
        this.chatListItems=chatListItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatlist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatListItem currentItem = chatListItems.get(position);

        // Bind your data to views here
        holder.chatName.setText(currentItem.getChatName()); // Set your actual chat name
        holder.messageText.setText(currentItem.getMessageText()); // Set your actual message

        if (currentItem.isDarkMode()) {
            holder.chatName.setTextColor(Color.parseColor("#FFFFFFFF"));
            holder.messageText.setTextColor(Color.parseColor("#FFFFFFFF"));
            // Set the background color of your item's layout as well if needed
        } else {
            holder.chatName.setTextColor(Color.parseColor("#FF000000"));
            holder.messageText.setTextColor(Color.parseColor("#FF000000"));
            // Set the background color of your item's layout as well if needed
        }
    }


    @Override
    public int getItemCount() {
        // Return the number of items
        return chatListItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // TextView properties
        TextView chatName;
        TextView messageText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chatName = itemView.findViewById(R.id.chat_name);
            messageText = itemView.findViewById(R.id.message_text);
        }
    }
}