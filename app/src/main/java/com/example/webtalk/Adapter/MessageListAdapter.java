package com.example.webtalk.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.ChatMessageDTO;

import java.util.ArrayList;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ChatAppMsgViewHolder> {
    private ArrayList<ChatMessageDTO> mMessageList;

    public MessageListAdapter(ArrayList<ChatMessageDTO> messageList) {
        mMessageList = messageList;
    }

    @Override
    public void onBindViewHolder(ChatAppMsgViewHolder holder, int position) {
        ChatMessageDTO msgDto = this.mMessageList.get(position);
        if(msgDto.MSG_TYPE_RECEIVED.equals(msgDto.getMsgType()))
        {
            holder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.leftMsgTextView.setText(msgDto.getMsgContent());
            holder.rightMsgLayout.setVisibility(LinearLayout.GONE);
        }
        else if(msgDto.MSG_TYPE_SENT.equals(msgDto.getMsgType()))
        {
            holder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.rightMsgTextView.setText(msgDto.getMsgContent());
            holder.leftMsgLayout.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public ChatAppMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.message, parent, false);
        return new ChatAppMsgViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(mMessageList==null)
        {
            mMessageList = new ArrayList<ChatMessageDTO>();
        }
        return mMessageList.size();
    }
    public class ChatAppMsgViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftMsgLayout;

        LinearLayout rightMsgLayout;

        TextView leftMsgTextView;

        TextView rightMsgTextView;

        public ChatAppMsgViewHolder(View itemView) {
            super(itemView);

            if(itemView!=null) {
                leftMsgLayout = (LinearLayout) itemView.findViewById(R.id.chat_left_msg_layout);
                rightMsgLayout = (LinearLayout) itemView.findViewById(R.id.chat_right_msg_layout);
                leftMsgTextView = (TextView) itemView.findViewById(R.id.chat_left_msg_text_view);
                rightMsgTextView = (TextView) itemView.findViewById(R.id.chat_right_msg_text_view);
            }
        }
    }
}