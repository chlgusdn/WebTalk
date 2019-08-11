package com.example.webtalk.Recycler_item_Class;

import com.example.webtalk.Fragment.Calendar;

public class Calendar_Item {
    private String username, makeDate, StateMessage, UserContext;
    int UserProfile;

    public Calendar_Item(String UserName, String MakeDate, String StateMessage, int UserProfile, String UserConText) {
        this.username = UserName;
        this.makeDate = MakeDate;
        this.StateMessage = StateMessage;
        this.UserProfile = UserProfile;
        this.UserContext = UserConText;
    }
    public String getUsername() {
        return this.username;
    }
    public String getMakeDate() {
        return this.makeDate;
    }
    public String getStateMessage (){
        return this.StateMessage;
    }
    public String getUserContext () {
        return this.UserContext;
    }
    public int getUserPrifile () {
        return  this.UserProfile;
    }
    public void setUsername(String userName) {
        this.username = userName;
    }
    public void setMakeDate (String makeDate) {
        this.makeDate = makeDate;
    }
    public void setStateMessage (String stateMessage) {
        this.StateMessage = stateMessage;
    }
    public void setUserContext (String userContext) {
        this.UserContext = userContext;
    }
    public void setUserProfile (int userProfile) {
        this.UserProfile = userProfile;
    }
}
