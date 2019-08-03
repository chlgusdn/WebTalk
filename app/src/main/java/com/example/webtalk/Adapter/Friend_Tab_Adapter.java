package com.example.webtalk.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Friend_Tab_item;

import java.util.ArrayList;

public class Friend_Tab_Adapter extends RecyclerView.Adapter<Friend_Tab_Adapter.Friend_Tab_View_Holder>{
    private ArrayList<Friend_Tab_item> friend_items;


    public Friend_Tab_Adapter(ArrayList<Friend_Tab_item> items) {
        this.friend_items = items;
    }

    @NonNull @Override
    public Friend_Tab_View_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_friend_tab_item,viewGroup,false);
        return new Friend_Tab_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Friend_Tab_View_Holder friend_tab_view_holder, int position) {
       friend_tab_view_holder.user_name.setText(friend_items.get(position).get_friend_user_name());
       friend_tab_view_holder.state_message.setText(friend_items.get(position).get_friend_state_message());
       friend_tab_view_holder.user_profile.setImageResource(friend_items.get(position).get_friend_user_profile());
    }

    @Override
    public int getItemCount() {
        return friend_items.size();
    }

    class Friend_Tab_View_Holder extends RecyclerView.ViewHolder {

        private TextView user_name;
        private TextView state_message;
        private ImageView user_profile;

        public Friend_Tab_View_Holder(View itemView) {
            super(itemView);
            user_name = (TextView)itemView.findViewById(R.id.chat_other_user);
            state_message = (TextView) itemView.findViewById(R.id.recycler_profile_state_message);
            user_profile = (ImageView) itemView.findViewById(R.id.chat_other_profile);
        }
    }
}

