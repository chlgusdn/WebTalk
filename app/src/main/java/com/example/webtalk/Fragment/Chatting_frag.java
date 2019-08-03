package com.example.webtalk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webtalk.Adapter.Chatting_Room_Adapter;
import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Chatting_Room_item;

import java.util.ArrayList;

public class Chatting_frag extends Fragment {

    private Chatting_Room_Adapter adapter;
    private ArrayList<Chatting_Room_item> chatting_array = new ArrayList<Chatting_Room_item>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_frag, container, false);

        RecyclerView chatting_room = view.findViewById(R.id.chat_room_recyclerview);
        chatting_room.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        chatting_room.setLayoutManager(layoutManager);

        chatting_array.add(new Chatting_Room_item("최현우","안녕",R.drawable.domain));
        chatting_array.add(new Chatting_Room_item("최현우","안녕",R.drawable.domain));
        chatting_array.add(new Chatting_Room_item("최현우","안녕",R.drawable.domain));
        adapter = new Chatting_Room_Adapter(chatting_array);
        chatting_room.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
