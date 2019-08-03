package com.example.webtalk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webtalk.Adapter.Friend_Tab_Adapter;
import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Friend_Tab_item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Friend extends Fragment {
    private Friend_Tab_Adapter adapter;
    private ArrayList<Friend_Tab_item >items =  new ArrayList<Friend_Tab_item>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        items.add(new Friend_Tab_item("qwe","hello",R.drawable.domain));
        items.add(new Friend_Tab_item("asd","hello",R.drawable.domain));
        items.add(new Friend_Tab_item("qwe","hello",R.drawable.domain));
        items.add(new Friend_Tab_item("zxc","hello",R.drawable.domain));
        items.add(new Friend_Tab_item("123s","hello",R.drawable.domain));
        adapter = new Friend_Tab_Adapter(items);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //end recyclerviewadapter
        return view;
    }
}
