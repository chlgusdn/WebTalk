package com.example.webtalk.Recycler_item_Class;

public class Friend_Tab_item {

    private String user_name;
    private String state_message;
    private int user_profile;
    public Friend_Tab_item(String name, String msg, int img) {
        this.user_name = name;
        this.state_message = msg;
        this.user_profile = img;
    }
    public String get_friend_user_name() {
       return user_name;
    }
    public String get_friend_state_message() {
        return state_message;
    }
    public int get_friend_user_profile() {
        return user_profile;
    }
}
