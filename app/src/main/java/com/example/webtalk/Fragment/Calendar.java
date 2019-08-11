package com.example.webtalk.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.webtalk.Adapter.Calendar_Apdapter;
import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Calendar_Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Calendar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        final Button calendarAdd = (Button)view.findViewById(R.id.add_calendar);
        final ArrayList<Calendar_Item> calendar_list = new ArrayList<Calendar_Item>();
        final SharedPreferences loginUserInfo = view.getContext().getSharedPreferences("userLoginInfomation", Context.MODE_PRIVATE);
        final RecyclerView calendar_recycler_view = (RecyclerView) view.findViewById(R.id.calendar_recycler_view);
        final Calendar_Apdapter calendar_apdapter = new Calendar_Apdapter(view.getContext(), calendar_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        calendar_recycler_view.setHasFixedSize(true);
        calendar_recycler_view.setLayoutManager(layoutManager);
        calendar_recycler_view.setAdapter(calendar_apdapter);
        calendarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Calendar.this.getContext());
                final EditText addCalendar = new EditText(Calendar.this.getContext());
                builder.setTitle("새로운 일정");
                builder.setMessage("새로운 일정을 등록해주세요.");
                builder.setView(addCalendar);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                        long nowDate = System.currentTimeMillis();
                        Date date = new Date(nowDate);
                        String nowDateFormat =  simpleDateFormat.format(date);
                        String userName = loginUserInfo.getString("userName","NULL");
                        String userStateMessage = loginUserInfo.getString("userStateMessage", "NULL");
                        calendar_list.add(new Calendar_Item(userName,nowDateFormat,userStateMessage, R.drawable.domain, addCalendar.getText().toString()));
                        calendar_apdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });

        return view;
    }
}
