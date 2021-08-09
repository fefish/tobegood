package com.example.tobegood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

//包名小写，类名大驼峰命名法，接口大驼峰命名法，方法小驼峰命名法，变量小驼峰命名法,xml全部小写，下划线，
public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
        }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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