package com.example.webtalk.Recycler_item_Class;

public class Chatting_Room_item {
    private  String other_user_name;
    private  String other_user_message;
    private int other_user_profile;

    public Chatting_Room_item(String name, String message, int img) {
        this.other_user_name = name;
        this.other_user_message = message;
        this.other_user_profile = img;
    }

    public String  get_other_user_name() {
        return other_user_name;
    }
    public String get_other_user_message() {
        return other_user_message;
    }
    public int get_other_user_profile() {
        return other_user_profile;
    }
}
