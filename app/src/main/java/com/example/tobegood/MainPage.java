package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.User;
import com.example.tobegood.dao.UserDao;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        /*get id from prepage start*/
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee",0);
        setToolbar(data);
/*        Toast.makeText(getApplicationContext(),"You have been to the main page! Your id is"+data,
                Toast.LENGTH_SHORT).show();*/
        /*get id from prepage end*/

        Button button_mainpage_eat = (Button) findViewById(R.id.button_mainpage_eat);
        Button button_mainpage_exercise = (Button) findViewById(R.id.button_mainpage_exercise);
        button_mainpage_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(MainPage.this, EatActivity.class);
                intent_toeatpage.putExtra("usee",data);
                startActivity(intent_toeatpage);
            }
        });
        button_mainpage_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(MainPage.this, ExerciseActivity.class);
                intent_toexercisepage.putExtra("usee",data);
                startActivity(intent_toexercisepage);
            }
        });

        setBottomBar(data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setday,menu);
        return true;
    }

    private  void setToolbar(int data){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        UserDao userDao = new UserDao(MainPage.this);
        User user = userDao.getUserById(data);
        toolbar.setTitle("tobegood");
        toolbar.setSubtitle("Welcome,"+user.getName()+"! This is the main page.");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
    }

    private void setBottomBar(int data){
        /*bottom bar function start*/
        ImageButton bottom_eat = (ImageButton) findViewById(R.id.bottom_eat);
        ImageButton bottom_exercise = (ImageButton) findViewById(R.id.bottom_exercise);
        ImageButton bottom_settings = (ImageButton) findViewById(R.id.bottom_settings);
        ImageButton bottom_help = (ImageButton) findViewById(R.id.bottom_help);
        bottom_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(MainPage.this, EatActivity.class);
                intent_toeatpage.putExtra("usee",data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(MainPage.this, ExerciseActivity.class);
                intent_toexercisepage.putExtra("usee",data);
                startActivity(intent_toexercisepage);
            }
        });
        bottom_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(MainPage.this, SettingsActivity.class);
                intent_toeatpage.putExtra("usee",data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(MainPage.this, HelpActivity.class);
                intent_toexercisepage.putExtra("usee",data);
                startActivity(intent_toexercisepage);
            }
        });

        /*bottom bar function end*/
    }
}