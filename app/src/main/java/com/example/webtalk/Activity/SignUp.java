package com.example.webtalk.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webtalk.R;
import com.example.webtalk.Recycler_item_Class.ChatMessageDTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private  PrintWriter printWriter;
    private  BufferedReader bufferedReader;
    private Socket socket;
    private String data;
    private BufferedWriter bufferedWriter;
    private Handler mhandler = new Handler();
    private ArrayList<String> user_infomation = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText username_textfield = (EditText)findViewById(R.id.sign_username_textfield);
        final EditText email_textfield = (EditText)findViewById(R.id.sign_email_textfield);
        final EditText password_textfield = (EditText)findViewById(R.id.sign_password_textfield);
        final EditText password_check_textfield = (EditText)findViewById(R.id.sign_password_check_textfield);
        final EditText phonenumber_textfield = (EditText)findViewById(R.id.sign_phone_textfield);
        Button signup_button = (Button)findViewById(R.id.signup_success);
        send_thread.start();
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password_textfield.getText().toString().equals(password_check_textfield.getText().toString())) {
                    user_infomation.add(username_textfield.getText().toString());
                    user_infomation.add(email_textfield.getText().toString()) ;
                    user_infomation.add(phonenumber_textfield.getText().toString());
                    user_infomation.add(password_textfield.getText().toString());
                    try {
                        printWriter.println(user_infomation);
                        Intent login_intent  = new Intent(getApplicationContext(), Login.class);
                        startActivity(login_intent);
                        finish();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 서로 다릅니다.",Toast.LENGTH_LONG);
                }
            }
        });
    }
    private Thread send_thread = new Thread() {
        @Override
        public void run() {
            try {
                setSocket("13.209.63.39", 9998);
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public void setSocket(String ip, int port)throws IOException {
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
    }
}
