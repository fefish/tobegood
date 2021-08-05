package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

//包名小写，类名大驼峰命名法，接口大驼峰命名法，方法小驼峰命名法，变量小驼峰命名法,xml全部小写，下划线，
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //File file = new File(System.getProperty("user.dir"));
        //Log.d("1111", String.valueOf(new File(file + "app/assets/EatTable_info.sql").exists()));
        setContentView(R.layout.activity_main);
        Button button_first_choice_login = (Button) findViewById(R.id.button_first_choice_login);
        Button button_first_choice_register = (Button) findViewById(R.id.button_first_choice_register);
        button_first_choice_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tologin = new Intent(MainActivity.this, Login.class);
                startActivity(intent_tologin);
            }
        });
        button_first_choice_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toregister = new Intent(MainActivity.this, Register.class);
                startActivity(intent_toregister);
            }
        });
        //UserDao userDao= new UserDao(MainActivity.this);
        //User user = new User();
        //userDao.add(user);
    }
}