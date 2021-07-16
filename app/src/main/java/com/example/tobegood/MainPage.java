package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tobegood.bean.EatTable;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Button button_mainpage_eat = (Button) findViewById(R.id.button_mainpage_eat);
        Button button_mainpage_exercise = (Button) findViewById(R.id.button_mainpage_exercise);
        button_mainpage_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(MainPage.this, EatActivity.class);
                startActivity(intent_toeatpage);
            }
        });
        button_mainpage_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(MainPage.this, EatActivity.class);
                startActivity(intent_toexercisepage);
            }
        });
        //这个要改
    }
}