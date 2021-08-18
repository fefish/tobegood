package com.example.tobegood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tobegood.bean.User;
import com.example.tobegood.bean.UserPlan;
import com.example.tobegood.bean.ExerciseTable;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.UserPlanDao;
import com.example.tobegood.dao.ExerciseTableDao;

import java.util.Calendar;

import static com.example.tobegood.CalenderFunction.addCalendarEvent;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*initialization*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseactivity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button Button_exercise_exercise1_detail = (Button) findViewById(R.id.Button_exercise_exercise1_detail);
        Button Button_exercise_exercise2_detail = (Button) findViewById(R.id.Button_exercise_exercise2_detail);
        Button Button_exercise_exercise3_detail = (Button) findViewById(R.id.Button_exercise_exercise3_detail);
        Button Button_exercise_exercise1_complete = (Button) findViewById(R.id.Button_exercise_exercise1_complete);
        Button Button_exercise_exercise2_complete = (Button) findViewById(R.id.Button_exercise_exercise2_complete);
        Button Button_exercise_exercise3_complete = (Button) findViewById(R.id.Button_exercise_exercise3_complete);
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee",0);
        setImageAndText(data);
        setToolbar(data);
        /*initialization end*/


        //get the user bean
        UserDao userDao = new UserDao(ExerciseActivity.this);
        User user = userDao.getUserById(data);
        UserPlanDao userPlanDao = new UserPlanDao(ExerciseActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(data+""+user.getLastDay());
        ExerciseTableDao exerciseTableDao = new ExerciseTableDao(ExerciseActivity.this);
        ExerciseTable exerciseTable = exerciseTableDao.getExerciseTableById(userPlan.getRecipeId());


        /*button function*/
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
                setComplete(data,1);
            }});
        Button_exercise_exercise2_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComplete(data,2);
            }});
        Button_exercise_exercise3_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComplete(data,3);
            }});

        setBottomBar(data);

    }

    private void setToolbar(int id){
        UserDao userDao = new UserDao(ExerciseActivity.this);
        User user = userDao.getUserById(id);
        int today = user.getLastDay();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("tobegood");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
        switch (today){
            case 1 :
                toolbar.setSubtitle("Welcome! This is the first day");
                break;
            case 2 :
                toolbar.setSubtitle("Welcome! This is the second day");
                break;
            case 3 :
                toolbar.setSubtitle("Welcome! This is the third day");
                break;
            case 4 :
                toolbar.setSubtitle("Welcome! This is the forth day");
                break;
            case 5 :
                toolbar.setSubtitle("Welcome! This is the fifth day");
                break;
            case 6 :
                toolbar.setSubtitle("Welcome! This is the sixth day");
                break;
            case 7 :
                toolbar.setSubtitle("Welcome! This is the seventh day");
                break;

        }
    }

    private void setImageAndText(int id){
        UserDao userDao = new UserDao(ExerciseActivity.this);
        User user = userDao.getUserById(id);
        UserPlanDao userPlanDao = new UserPlanDao(ExerciseActivity.this);
        int today = user.getLastDay();
        UserPlan userPlan = userPlanDao.getUserPlanById(id+""+today);
        ExerciseTableDao exerciseTableDao = new ExerciseTableDao(ExerciseActivity.this);
        ExerciseTable exerciseTable = exerciseTableDao.getExerciseTableById(userPlan.getRecipeId());
        ImageView Image_exercise_exercise1 = (ImageView) findViewById(R.id.Image_exercise_exercise1);
        ImageView Image_exercise_exercise2 = (ImageView) findViewById(R.id.Image_exercise_exercise2);
        ImageView Image_exercise_exercise3 = (ImageView) findViewById(R.id.Image_exercise_exercise3);
        TextView Text_exercise_exercise1_name = (TextView) findViewById(R.id.Text_exercise_exercise1_name );
        TextView Text_exercise_exercise2_name = (TextView) findViewById(R.id.Text_exercise_exercise2_name );
        TextView Text_exercise_exercise3_name = (TextView) findViewById(R.id.Text_exercise_exercise3_name );
        Text_exercise_exercise1_name.setText(exerciseTable.getExerciseOneName());
        Text_exercise_exercise2_name.setText(exerciseTable.getExerciseTwoName());
        Text_exercise_exercise3_name.setText(exerciseTable.getExerciseThreeName());
        if(userPlan.getFirstExerciseComplete()==false) {
            int resID = getResources().getIdentifier(exerciseTable.getExerciseOnePic(), "drawable", "com.example.tobegood");
            Image_exercise_exercise1.setImageDrawable(getResources().getDrawable(resID));
        }else {
            Image_exercise_exercise1.setImageResource(R.drawable.complete); }
        if(userPlan.getSecondExerciseComplete()==false) {
            int resID = getResources().getIdentifier(exerciseTable.getExerciseTwoPic(), "drawable", "com.example.tobegood");
            Image_exercise_exercise2.setImageDrawable(getResources().getDrawable(resID));
        }else {
            Image_exercise_exercise2.setImageResource(R.drawable.complete); }
        if(userPlan.getThirdExerciseComplete()==false) {
            int resID = getResources().getIdentifier(exerciseTable.getExerciseThreePic(), "drawable", "com.example.tobegood");
            Image_exercise_exercise3.setImageDrawable(getResources().getDrawable(resID));
        }else {
            Image_exercise_exercise3.setImageResource(R.drawable.complete); }
    }

    private void updateToday(int id, int today){
        UserDao userDao = new UserDao(ExerciseActivity.this);
        User user = userDao.getUserById(id);
        user.setLastDay(today);
        userDao.update(user);
    }

    private void setComplete(int id , int num){
        UserDao userDao = new UserDao(ExerciseActivity.this);
        User user = userDao.getUserById(id);
        UserPlanDao userPlanDao = new UserPlanDao(ExerciseActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(id+""+user.getLastDay());
        ExerciseTableDao exerciseTableDao = new ExerciseTableDao(ExerciseActivity.this);
        ExerciseTable exerciseTable = exerciseTableDao.getExerciseTableById(userPlan.getRecipeId());
        switch (num){
            case 1:
                userPlan.setFirstExerciseComplete(!userPlan.getFirstExerciseComplete());
                break;
            case 2:
                userPlan.setSecondExerciseComplete(!userPlan.getSecondExerciseComplete());
                break;
            case 3:
                userPlan.setThirdExerciseComplete(!userPlan.getThirdExerciseComplete());
                break;
        }
        userPlanDao.update(userPlan);
        setImageAndText(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setdayandremind, menu);
        return true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee",0);
        switch (item.getItemId()) {
            case R.id.firstday:
                updateToday(data,1);
                break;
            case R.id.secondday:
                updateToday(data,2);
                break;
            case R.id.thirdday:
                updateToday(data,3);
                break;
            case R.id.forthday:
                updateToday(data,4);
                break;
            case R.id.fifthday:
                updateToday(data,5);
                break;
            case R.id.sixthday:
                updateToday(data,6);
                break;
            case R.id.seventhday:
                updateToday(data,7);
                break;
            case R.id.clock1:
                setClock(1);
                break;
            case R.id.clock2:
                setClock(2);
                break;
            case R.id.clock3:
                setClock(3);
                break;
        }
        setToolbar(data);
        setImageAndText(data);
        return super.onOptionsItemSelected(item);
    }

    private void setClock(int num) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ExerciseActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                switch (num) {
                    case 1:
                        addCalendarEvent(ExerciseActivity.this, "It's time to exercise, do your first exercise!", "Don't forget to do your first exercise!", hourOfDay, minute);
                        Toast.makeText(ExerciseActivity.this, "OK" + hourOfDay + minute, Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        addCalendarEvent(ExerciseActivity.this, "It's time to exercise, do your second exercise!", "Don't forget to do your second exercise!", hourOfDay, minute);
                        Toast.makeText(ExerciseActivity.this, "OK" + hourOfDay + minute, Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        addCalendarEvent(ExerciseActivity.this, "It's time to exercise, do your third exercise!", "Don't forget to do your third exercise!", hourOfDay, minute);
                        Toast.makeText(ExerciseActivity.this, "OK" + hourOfDay + minute, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, hour, minute, true);
        timePickerDialog.show();
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

    private void setBottomBar(int data){
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
    }
}