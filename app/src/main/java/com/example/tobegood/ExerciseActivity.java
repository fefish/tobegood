package com.example.tobegood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.User;
import com.example.tobegood.bean.UserPlan;
import com.example.tobegood.bean.ExerciseTable;
import com.example.tobegood.dao.EatTableDao;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.UserPlanDao;
import com.example.tobegood.dao.ExerciseTableDao;

public class ExerciseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setday, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("tobegood");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
        switch (item.getItemId()) {

            case R.id.firstday:
                toolbar.setSubtitle("Welcome! This is first day");
                break;
            case R.id.secondday:
                toolbar.setSubtitle("Welcome! This is second day");
                break;
            case R.id.thirdday:
                toolbar.setSubtitle("Welcome! This is third day");
                break;
            case R.id.forthday:
                toolbar.setSubtitle("Welcome! This is forth day");
                break;
            case R.id.fifthday:
                toolbar.setSubtitle("Welcome! This is fifth day");
                break;
            case R.id.sixthday:
                toolbar.setSubtitle("Welcome! This is sixth day");
                break;
            case R.id.seventhday:
                toolbar.setSubtitle("Welcome! This is seventh day");
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseactivity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView Image_exercise_exercise1 = (ImageView) findViewById(R.id.Image_exercise_exercise1);
        ImageView Image_exercise_exercise2 = (ImageView) findViewById(R.id.Image_exercise_exercise2);
        ImageView Image_exercise_exercise3 = (ImageView) findViewById(R.id.Image_exercise_exercise3);
        Button Button_exercise_exercise1_detail = (Button) findViewById(R.id.Button_exercise_exercise1_detail);
        Button Button_exercise_exercise2_detail = (Button) findViewById(R.id.Button_exercise_exercise2_detail);
        Button Button_exercise_exercise3_detail = (Button) findViewById(R.id.Button_exercise_exercise3_detail);
        Button Button_exercise_exercise1_complete = (Button) findViewById(R.id.Button_exercise_exercise1_complete);
        Button Button_exercise_exercise2_complete = (Button) findViewById(R.id.Button_exercise_exercise2_complete);
        Button Button_exercise_exercise3_complete = (Button) findViewById(R.id.Button_exercise_exercise3_complete);

        //get the user detail
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee",0);

        //get the user bean
        UserDao userDao = new UserDao(ExerciseActivity.this);
        User user = userDao.getUserById(data);
        int today = user.getLastday();
        UserPlanDao userPlanDao = new UserPlanDao(ExerciseActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(data+""+today);
        ExerciseTableDao exerciseTableDao = new ExerciseTableDao(ExerciseActivity.this);
        ExerciseTable exerciseTable = exerciseTableDao.getExerciseTableById(userPlan.getRecipeId());
        if (exerciseTable.getExerciseOnePic().equals("recipe001")){
            Image_exercise_exercise1.setImageResource(R.drawable.recipe001);
        }
        Button_exercise_exercise1_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(exerciseTable,1);
            }});
        Button_exercise_exercise2_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(exerciseTable,2);
            }});
        Button_exercise_exercise3_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(exerciseTable,3);
            }});
        Button_exercise_exercise1_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_exercise_exercise1.setImageResource(R.drawable.complete);
            }});
        Button_exercise_exercise2_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_exercise_exercise2.setImageResource(R.drawable.complete);
            }});
        Button_exercise_exercise3_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_exercise_exercise3.setImageResource(R.drawable.complete);
            }});

        /*bottom bar function start*/
        ImageButton bottom_eat = (ImageButton) findViewById(R.id.bottom_eat);
        ImageButton bottom_exercise = (ImageButton) findViewById(R.id.bottom_exercise);
        ImageButton bottom_settings = (ImageButton) findViewById(R.id.bottom_settings);
        ImageButton bottom_help = (ImageButton) findViewById(R.id.bottom_help);
        bottom_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(ExerciseActivity.this, EatActivity.class);
                intent_toeatpage.putExtra("usee",data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                intent_toexercisepage.putExtra("usee",data);
                startActivity(intent_toexercisepage);
            }
        });
        bottom_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(ExerciseActivity.this, SettingsActivity.class);
                intent_toeatpage.putExtra("usee",data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(ExerciseActivity.this, HelpActivity.class);
                intent_toexercisepage.putExtra("usee",data);
                startActivity(intent_toexercisepage);
            }
        });

        /*bottom bar function end*/

    }
    private void detailDialog(ExerciseTable exerciseTable,int recipenum){

        AlertDialog dialog = new AlertDialog.Builder (this).create ();
        dialog.setTitle ("This is the details about your recipe:");
        switch (recipenum) {
            case 1:
                dialog.setMessage (exerciseTable.getExerciseOneContent());
                break;
            case 2:
                dialog.setMessage (exerciseTable.getExerciseTwoContent());
                break;
            case 3:
                dialog.setMessage (exerciseTable.getExerciseThreeContent());
                break;
            default:
                break;
        }
        dialog.setButton (DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText (ExerciseActivity.this,"OK",Toast.LENGTH_LONG).show ();
            }
        });
        dialog.show ();
    }
}