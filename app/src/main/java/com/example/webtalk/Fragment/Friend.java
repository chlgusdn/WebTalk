package com.example.webtalk.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webtalk.Adapter.Friend_Tab_Adapter;
import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Friend_Tab_item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Friend extends Fragment {
    private Friend_Tab_Adapter adapter;
    private ArrayList<Friend_Tab_item >items =  new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        SharedPreferences userinfoPreference = view.getContext().getSharedPreferences("userOffLineInfomation", Context.MODE_PRIVATE);
        SharedPreferences userLoginOnceCheckPreference = view.getContext().getSharedPreferences("userLoginInfomation",Context.MODE_PRIVATE);
        TextView userNameText = view.findViewById(R.id.profile_user_name);
        TextView userMessagaeText = view.findViewById(R.id.profile_state_message);

        if (!userLoginOnceCheckPreference.getString("userName","").equals("")) {
            userNameText.setText(userLoginOnceCheckPreference.getString("userName",""));
            userMessagaeText.setText(userLoginOnceCheckPreference.getString("userStateMessage",""));
        }
        else {
            userNameText.setText(userinfoPreference.getString("offlineUserName","NULL"));
            userMessagaeText.setText(userinfoPreference.getString("offlineUserMessage","NULL"));
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Friend_Tab_Adapter(items);
        recyclerView.setAdapter(adapter);

        Bundle bundle =getArguments();
        String AddUserName = "NULL";
        String AddUserStateMessage = "NULL";
        try {
            if (!bundle.getString("AddFriendUserName").isEmpty()) {
                AddUserName = bundle.getString("AddFriendUserName");
                AddUserStateMessage = bundle.getString("AddFriendUserMessage");
            }

            if (!AddUserName.equals("NULL") && !AddUserStateMessage.equals("NULL")) {
                items.add(new Friend_Tab_item(AddUserName,AddUserStateMessage,R.drawable.domain));
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (items.isEmpty()) {
            ConstraintLayout nodata_view = view.findViewById(R.id.nodata_view);
            nodata_view.setVisibility(view.VISIBLE);
            recyclerView.setVisibility(view.GONE);
        }
        else {
            adapter.notifyDataSetChanged();
        }
        return view;
    }
}
