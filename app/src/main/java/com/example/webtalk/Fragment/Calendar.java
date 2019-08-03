package com.example.webtalk.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webtalk.Adapter.Calendar_Apdapter;
import com.example.webtalk.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Calendar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        ArrayList<HashMap<String, String>> calendar_list = new ArrayList<HashMap<String, String>>();
        RecyclerView calendar_recycler_view = (RecyclerView) view.findViewById(R.id.calendar_recycler_view);
        Calendar_Apdapter calendar_apdapter = new Calendar_Apdapter(view.getContext(), calendar_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        calendar_recycler_view.setHasFixedSize(true);
        calendar_recycler_view.setLayoutManager(layoutManager);

        calendar_recycler_view.setAdapter(calendar_apdapter);

        return view;
    }
}
