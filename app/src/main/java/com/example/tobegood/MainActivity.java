package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_first_choice_login = (Button) findViewById(R.id.button_first_choice_login);
        Button button_first_choice_register = (Button) findViewById(R.id.button_first_choice_register);
        button_first_choice_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tologin = new Intent(MainActivity.this, register.class);
                startActivity(intent_tologin);
            }
        });
//      这里有一个问题，创建的早，那个intent_tologin要改成去登录页面。
        button_first_choice_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toregister = new Intent(MainActivity.this, mainpage.class);
                startActivity(intent_toregister);
            }
        });
//      这里有一个问题，创建的早，那个intent_toregister要改成去注册页面。
    }
}