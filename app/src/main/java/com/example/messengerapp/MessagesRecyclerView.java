package com.example.messengerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerapp.R;

import java.util.ArrayList;

public class MessagesRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> messageTexts;
    Context context;

    public MessagesRecyclerView(Context context, ArrayList<String> messageTexts) {
        this.context = context;
        this.messageTexts = messageTexts;
    }

    int i=0;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate different layouts based on viewType
        if (i <= 5 && i >= 3) {
            View sentView = inflater.inflate(R.layout.message_backgorund, parent, false);
            i++;
            return new SentViewHolder(sentView);
        } else {
            View receivedView = inflater.inflate(R.layout.received_message_backgorund, parent, false);
            i++;
            return new ReceivedViewHolder(receivedView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String message = messageTexts.get(position);

        // Bind data based on the holder's type
        if (holder instanceof SentViewHolder) {
            ((SentViewHolder) holder).messageText.setText(message);
        } else if (holder instanceof ReceivedViewHolder) {
            ((ReceivedViewHolder) holder).receivedMessageText.setText(message);
        }
    }

    @Override
    public int getItemCount() {
        return messageTexts.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Return different view types based on your condition
        // For now, let's assume even positions are sent and odd positions are received
        return messageTexts.size();
    }

    public static class SentViewHolder extends RecyclerView.ViewHolder {
        // TextView properties
        TextView messageText;

        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
        }
    }

    public static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        // TextView properties
        TextView receivedMessageText;

        public ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            receivedMessageText = itemView.findViewById(R.id.receivedMessageText);
        }
    }
}
