package com.example.webtalk.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.webtalk.Fragment.Calendar;
import com.example.webtalk.Fragment.Chatting_frag;
import com.example.webtalk.Fragment.Friend;
import com.example.webtalk.Fragment.Mypage;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int TabNum;

    public ViewPagerAdapter(FragmentManager fm, int TabNumber) {
        super(fm);
        this.TabNum = TabNumber;
    }
    @Override
    public int getCount() {
        return TabNum;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Friend friend_page = new Friend();
                return friend_page;
            case 1:
                Chatting_frag chatting_page = new Chatting_frag();
                return chatting_page;
            case 2:
                Calendar calendar_page = new Calendar();
                return calendar_page;
            case 3:
                Mypage mypage = new Mypage();
                return mypage;
            default:
                return null;
        }
    }

}
