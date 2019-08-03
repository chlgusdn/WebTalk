package com.example.webtalk.Activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webtalk.Adapter.Chatting_Room_Adapter;
import com.example.webtalk.Adapter.MessageListAdapter;
import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.ChatMessageDTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class chatting_main extends AppCompatActivity {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private BufferedWriter bufferedWriter;
    private Socket socket;
    private TextView Recive_message;
    private String data;
    private Handler mhandler = new Handler();
    private  MessageListAdapter chatAppMsgAdapter;
    private ArrayList<ChatMessageDTO> msgDtoList = new ArrayList<ChatMessageDTO>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_main);
        Recive_message  = (TextView)findViewById(R.id.chat_left_msg_text_view);
        // Get RecyclerView object.
        final RecyclerView msgRecyclerView = (RecyclerView)findViewById(R.id.chat_recycler_view);

        // Set RecyclerView layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        // Create the initial data list.
        final ArrayList<ChatMessageDTO> msgDtoList = new ArrayList<ChatMessageDTO>();
        ChatMessageDTO msgDto = new ChatMessageDTO(ChatMessageDTO.MSG_TYPE_RECEIVED, "hello");
        msgDtoList.add(msgDto);

        // Create the data adapter with above data list.
        chatAppMsgAdapter = new MessageListAdapter(msgDtoList);

        // Set data adapter to RecyclerView.
        msgRecyclerView.setAdapter(chatAppMsgAdapter);

        final EditText msgInputText = (EditText)findViewById(R.id.chat_input_msg);

        Button msgSendButton = (Button)findViewById(R.id.chat_send_msg);

        msgSendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String msgContent = msgInputText.getText().toString();
                if(!TextUtils.isEmpty(msgContent))
                {
                    data = msgContent;
                    PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
                    printWriter.println(msgContent);
                    // Add a new sent message to the list.
                    msgDtoList.add(new ChatMessageDTO(ChatMessageDTO.MSG_TYPE_SENT, msgContent));

                    int newMsgPosition = msgDtoList.size() - 1;

                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    // Empty the input edit text box.
                    msgInputText.setText("");
                }
            }
        });
    }
    private Thread recive_Thread = new Thread() {
        public void run() {
//            Looper.prepare();
//            mhandler = new Handler();
//            Looper.loop();
           try {
                   setSocket("13.209.63.39", 9999);
                   printWriter = new PrintWriter(socket.getOutputStream(), true);
                   bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   printWriter.println("asd");
                   printWriter.println("testandroid");
               }catch (IOException e) {
                   e.printStackTrace();
               }
               try {
                   while (true) {
                       data = bufferedReader.readLine();
                       mhandler.post(new Runnable() {
                           @Override
                           public void run() {
                               msgDtoList.add(new ChatMessageDTO(ChatMessageDTO.MSG_TYPE_RECEIVED, data));
                               chatAppMsgAdapter.notifyDataSetChanged();
                               System.out.println(msgDtoList);
                           }
                       });
                   }
               }catch (IOException e) {
                   e.printStackTrace();
           }
        }
    };

    public void setSocket(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    protected  void onStop() {
        super.onStop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void onResume() {
        super.onResume();
        recive_Thread.start();
    }
}
