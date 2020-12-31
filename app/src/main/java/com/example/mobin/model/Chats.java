package com.example.mobin.model;

public class Chats {
    private String SENDER;
    private String Reciever;
    private String Message;

    public Chats(String SENDER, String reciever, String message) {
        this.SENDER = SENDER;
        Reciever = reciever;
        Message = message;
    }

    public Chats() {
    }

    public String getSENDER() {
        return SENDER;
    }

    public void setSENDER(String SENDER) {
        this.SENDER = SENDER;
    }

    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
