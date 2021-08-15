package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tobegood.bean.User;
import com.example.tobegood.dao.UserDao;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button Button_help_call = (Button) findViewById(R.id.Button_help_call);
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee", 0);
        setToolbar(data);
        setBottomBar(data);
        initialPage(data);
    }

    private void setToolbar(int data) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        UserDao userDao = new UserDao(HelpActivity.this);
        User user = userDao.getUserById(data);
        toolbar.setTitle("tobegood");
        toolbar.setSubtitle("Welcome, " + user.getName() + "! You can change your personal details.");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
    }

    private void setBottomBar(int data) {
        ImageButton bottom_eat = (ImageButton) findViewById(R.id.bottom_eat);
        ImageButton bottom_exercise = (ImageButton) findViewById(R.id.bottom_exercise);
        ImageButton bottom_settings = (ImageButton) findViewById(R.id.bottom_settings);
        ImageButton bottom_help = (ImageButton) findViewById(R.id.bottom_help);
        bottom_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(HelpActivity.this, EatActivity.class);
                intent_toeatpage.putExtra("usee", data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(HelpActivity.this, ExerciseActivity.class);
                intent_toexercisepage.putExtra("usee", data);
                startActivity(intent_toexercisepage);
            }
        });
        bottom_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(HelpActivity.this, SettingsActivity.class);
                intent_toeatpage.putExtra("usee", data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(HelpActivity.this, HelpActivity.class);
                intent_toexercisepage.putExtra("usee", data);
                startActivity(intent_toexercisepage);
            }
        });
    }

    private void initialPage(int data){
        TextView Text_help_number = (TextView) findViewById(R.id.Text_help_number);
        UserDao userDao = new UserDao(HelpActivity.this);
        User user = userDao.getUserById(data);
        Text_help_number.setText(user.getEmergencynumber());
    }
}