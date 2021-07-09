package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//包名小写，类名大驼峰命名法，接口大驼峰命名法，方法小驼峰命名法，变量小驼峰命名法
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
                Intent intent_tologin = new Intent(MainActivity.this, Register.class);
                startActivity(intent_tologin);
            }
        });
//      这里有一个问题，创建的早，那个intent_tologin要改成去登录页面。
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



//      这里有一个问题，创建的早，那个intent_toregister要改成去注册页面。
    }
}