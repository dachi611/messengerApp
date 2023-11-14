package com.example.messengerapp;


public class MessageBody {
    String Message;

    public MessageBody(String chatName) {
        this.Message = chatName;
    }

    public String getChatName() {
        return Message;
    }

    public void setChatName(String chatName) {
        this.Message = chatName;
    }
}
