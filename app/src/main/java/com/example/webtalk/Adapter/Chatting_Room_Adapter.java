package com.example.webtalk.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webtalk.Activity.chatting_main;
import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Chatting_Room_item;

import java.util.ArrayList;

public class Chatting_Room_Adapter extends RecyclerView.Adapter<Chatting_Room_Adapter.chatting_room_view_holer> {

    private ArrayList<Chatting_Room_item> chatting_array = new ArrayList<Chatting_Room_item>();


    public Chatting_Room_Adapter(ArrayList<Chatting_Room_item> chatting_room_items) {
        this.chatting_array = chatting_room_items;
    }

    @NonNull
    @Override
    public chatting_room_view_holer onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_chatting_item,viewGroup, false);
        return new chatting_room_view_holer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatting_room_view_holer chatting_room_view_holer, int position) {
        chatting_room_view_holer.other_user_name.setText(chatting_array.get(position).get_other_user_name());
        chatting_room_view_holer.other_user_message.setText(chatting_array.get(position).get_other_user_message());
        chatting_room_view_holer.other_user_profile.setImageResource(chatting_array.get(position).get_other_user_profile());
        chatting_room_view_holer.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), chatting_main.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatting_array.size();
    }

    class chatting_room_view_holer extends RecyclerView.ViewHolder {

    private TextView other_user_name;
    private TextView other_user_message;
    private ImageView other_user_profile;

    public chatting_room_view_holer(@NonNull View itemView) {
        super(itemView);
        other_user_name = (TextView)itemView.findViewById(R.id.chat_other_user);
        other_user_message = (TextView)itemView.findViewById(R.id.chat_other_user_message);
        other_user_profile = (ImageView)itemView.findViewById(R.id.chat_other_profile);
    }
}
}
