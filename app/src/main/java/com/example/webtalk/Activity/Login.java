package com.example.webtalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webtalk.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private String checkUserEmail;
    private String checkUserPassword;
    private String user_email;
    private String user_password;
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
                printWriter.println(user_email);
                printWriter.flush();
                 if (emailTextField.getText().toString().equals(checkUserEmail) && passwordTextField.getText().toString().equals(checkUserPassword)){
                     Toast.makeText(getApplicationContext(),"확인 되었습니다.",Toast.LENGTH_LONG).show();
                     Intent main_intent = new Intent(getApplicationContext(), MainActivity.class);
                     startActivity(main_intent);
                     finish();
                }else {
                    Toast.makeText(getApplicationContext(),"아이디 혹은 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
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
    private Thread reciveThread  = new Thread() {
        String[] dataArr;

        @Override
        public void run() {
            setSoket("13.209.63.39", 9997);
            try {
                String data = bufferedReader.readLine();
                dataArr = data.split(",");
                System.out.print(dataArr);
                checkUserEmail = dataArr[1];
                checkUserPassword = dataArr[2];
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

