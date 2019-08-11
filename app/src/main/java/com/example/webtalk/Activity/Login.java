package com.example.webtalk.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webtalk.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Login extends AppCompatActivity {
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    public String LoginUserName;
    public String LoginUserProfile;
    public String LoginUserStateMessage;
    public int    LoginUserPhoneNumber;
    private String checkUserEmail;
    private String checkUserPassword;
    private String user_email;
    private String user_password;
    public boolean isNetWork = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reciveThread.start();
        final EditText emailTextField = (EditText) findViewById(R.id.login_email_textfield);
        final EditText passwordTextField = (EditText) findViewById(R.id.login_password_textfield);

        Button signup_button = (Button) findViewById(R.id.signup);
        Button login_button = (Button) findViewById(R.id.login);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_email = emailTextField.getText().toString();
                user_password = passwordTextField.getText().toString();

                try {
                    printWriter.println(user_email);
                    printWriter.flush();
                }catch (NullPointerException e){
                    isNetWork = false;
                    Toast.makeText(getApplicationContext(),"Wi-Fi 및 데이터 네트워크에 접속하지 못하였습니다.",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder off_line_connect_builder = new AlertDialog.Builder(Login.this);
                    off_line_connect_builder.setTitle("오프라인으로 접속");
                    off_line_connect_builder.setMessage("오프라인으로 접속하시겠습니까?");
                    off_line_connect_builder.setPositiveButton("접속", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences offlinePreference = getSharedPreferences("userOffLineInfomation", MODE_PRIVATE);
                            SharedPreferences.Editor editor = offlinePreference.edit();
                            editor.putString("offlineUserName", "오프라인상태입니다.");
                            editor.putString("offlineUserMessage", "오프라인 상태입니다.");
                            editor.apply();
                            Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(main_intent);
                            finish();
                        }
                    });
                    off_line_connect_builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    off_line_connect_builder.show();
                }
                if (emailTextField.getText().toString().equals(checkUserEmail) && passwordTextField.getText().toString().equals(checkUserPassword)){
                    Toast.makeText(getApplicationContext(),"확인 되었습니다.",Toast.LENGTH_LONG).show();
                    Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                    SharedPreferences userPreference = getSharedPreferences("userLoginInfomation", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPreference.edit();
                    editor.putString("userName", LoginUserName);
                    editor.putInt("userPhoneNumber",LoginUserPhoneNumber);
                    editor.putString("userStateMessage", LoginUserStateMessage);
                    editor.apply();
                    startActivity(main_intent);
                    finish();
                }else {
                    if (isNetWork) {
                        Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_page_intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(signup_page_intent);
                finish();

            }
        });
    }
    public Thread reciveThread  = new Thread() {
        String[] dataArr;

        @Override
        public void run() {
            setSoket("13.209.63.39", 9997);
            try {
                while (socket!= null && socket.isConnected()) {
                        String data = bufferedReader.readLine();
                        dataArr = data.split(",");
                        System.out.print(dataArr);
                        LoginUserName = dataArr[0];
                        checkUserEmail = dataArr[1];
                        checkUserPassword = dataArr[2];
                        LoginUserPhoneNumber = Integer.parseInt(dataArr[3]);
                        LoginUserStateMessage = dataArr[4];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void setSoket(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

