package com.example.webtalk.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.Calendar_Item;

import java.util.ArrayList;

public class Calendar_Apdapter extends RecyclerView.Adapter<Calendar_Apdapter.calendar_adapter_view_holder> {
    ArrayList<Calendar_Item> calendar_list;
    Context  context;
    public Calendar_Apdapter(Context conxt, ArrayList<Calendar_Item> calendar) {
        this.calendar_list = calendar;
        this.context = conxt;
    }

    @NonNull
    @Override
    public calendar_adapter_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final calendar_adapter_view_holder calendar_adapter_view_holder, int position) {
        calendar_adapter_view_holder.calendar_user_name.setText(calendar_list.get(position).getUsername());
        calendar_adapter_view_holder.calendar_user_state_message.setText(calendar_list.get(position).getStateMessage());
        calendar_adapter_view_holder.calendar_text.setText(calendar_list.get(position).getUserContext());
        calendar_adapter_view_holder.calendar_make_date.setText(calendar_list.get(position).getMakeDate());
        calendar_adapter_view_holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.calendar_list.size();
    }

    public class calendar_adapter_view_holder extends RecyclerView.ViewHolder {
        TextView calendar_user_name;
        TextView calendar_user_state_message;
        TextView calendar_text;
        TextView calendar_make_date;
        ImageView calendar_user_image;
        CardView cardView;

        public calendar_adapter_view_holder(View itemView) {
            super(itemView);
            calendar_user_name = (TextView)itemView.findViewById(R.id.calendar_user_name);
            calendar_user_image = (ImageView)itemView.findViewById(R.id.calendar_user_image);
            calendar_user_state_message = (TextView)itemView.findViewById(R.id.calendar_user_state_message);
            calendar_text = (TextView)itemView.findViewById(R.id.calendar_text);
            calendar_make_date = (TextView)itemView.findViewById(R.id.user_make_date);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
        }
    }
}
