package com.example.tobegood;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.User;
import com.example.tobegood.bean.UserPlan;
import com.example.tobegood.dao.EatTableDao;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.UserPlanDao;

import java.util.Calendar;


import static com.example.tobegood.CalenderFunction.addCalendarEvent;

//import android.widget.Toolbar;


public class EatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*initialization*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button Button_eat_recipe1_detail = (Button) findViewById(R.id.Button_eat_recipe1_detail);
        Button Button_eat_recipe2_detail = (Button) findViewById(R.id.Button_eat_recipe2_detail);
        Button Button_eat_recipe3_detail = (Button) findViewById(R.id.Button_eat_recipe3_detail);
        Button Button_eat_recipe1_complete = (Button) findViewById(R.id.Button_eat_recipe1_complete);
        Button Button_eat_recipe2_complete = (Button) findViewById(R.id.Button_eat_recipe2_complete);
        Button Button_eat_recipe3_complete = (Button) findViewById(R.id.Button_eat_recipe3_complete);
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee", 0);
        setImageAndText(data);
        setToolbar(data);
        if (!checkPermission()) {
            Toast.makeText(EatActivity.this, "请允许使用日历权限", Toast.LENGTH_LONG).show();
        }

        /*initialization end*/

        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(data);
        UserPlanDao userPlanDao = new UserPlanDao(EatActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(data + "" + user.getLastDay());
        EatTableDao eatTableDao = new EatTableDao(EatActivity.this);
        EatTable eatTable = eatTableDao.getEatTableById(userPlan.getRecipeId());

        /*button function*/
        Button_eat_recipe1_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(data, 1);
            }
        });
        Button_eat_recipe2_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(data, 2);
            }
        });
        Button_eat_recipe3_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(data, 3);
            }
        });
        Button_eat_recipe1_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComplete(data, 1);


            }
        });
        Button_eat_recipe2_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComplete(data, 2);
            }
        });
        Button_eat_recipe3_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setComplete(data, 3);
            }
        });

        /*button function end*/

        setBottomBar(data);
    }

    private void setToolbar(int id) {
        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(id);
        int today = user.getLastDay();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("tobegood");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
        switch (today) {
            case 1:
                toolbar.setSubtitle("Welcome! This is the first day");
                break;
            case 2:
                toolbar.setSubtitle("Welcome! This is the second day");
                break;
            case 3:
                toolbar.setSubtitle("Welcome! This is the third day");
                break;
            case 4:
                toolbar.setSubtitle("Welcome! This is the forth day");
                break;
            case 5:
                toolbar.setSubtitle("Welcome! This is the fifth day");
                break;
            case 6:
                toolbar.setSubtitle("Welcome! This is the sixth day");
                break;
            case 7:
                toolbar.setSubtitle("Welcome! This is the seventh day");
                break;

        }
    }

    private void setImageAndText(int id) {
        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(id);
        UserPlanDao userPlanDao = new UserPlanDao(EatActivity.this);
        int today = user.getLastDay();
        UserPlan userPlan = userPlanDao.getUserPlanById(id + "" + today);
        EatTableDao eatTableDao = new EatTableDao(EatActivity.this);
        EatTable eatTable = eatTableDao.getEatTableById(userPlan.getRecipeId());
        ImageView Image_eat_recipe1 = (ImageView) findViewById(R.id.Image_eat_recipe1);
        ImageView Image_eat_recipe2 = (ImageView) findViewById(R.id.Image_eat_recipe2);
        ImageView Image_eat_recipe3 = (ImageView) findViewById(R.id.Image_eat_recipe3);
        TextView Text_eat_recipe1_name = (TextView) findViewById(R.id.Text_eat_recipe1_name);
        TextView Text_eat_recipe2_name = (TextView) findViewById(R.id.Text_eat_recipe2_name);
        TextView Text_eat_recipe3_name = (TextView) findViewById(R.id.Text_eat_recipe3_name);
        Text_eat_recipe1_name.setText(eatTable.getRecipeOneName());
        Text_eat_recipe2_name.setText(eatTable.getRecipeTwoName());
        Text_eat_recipe3_name.setText(eatTable.getRecipeThreeName());
        if (userPlan.getFirstRecipeComplete() == false) {
            int resID = getResources().getIdentifier(eatTable.getRecipeOnePic(), "drawable", "com.example.tobegood");
            Image_eat_recipe1.setImageDrawable(getResources().getDrawable(resID));
        } else {
            Image_eat_recipe1.setImageResource(R.drawable.complete);
        }
        if (userPlan.getSecondRecipeComplete() == false) {
            int resID = getResources().getIdentifier(eatTable.getRecipeTwoPic(), "drawable", "com.example.tobegood");
            Image_eat_recipe2.setImageDrawable(getResources().getDrawable(resID));
        } else {
            Image_eat_recipe2.setImageResource(R.drawable.complete);
        }
        if (userPlan.getThirdRecipeComplete() == false) {
            int resID = getResources().getIdentifier(eatTable.getRecipeThreePic(), "drawable", "com.example.tobegood");
            Image_eat_recipe3.setImageDrawable(getResources().getDrawable(resID));
        } else {
            Image_eat_recipe3.setImageResource(R.drawable.complete);
        }
    }

    private void updateToday(int id, int today) {
        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(id);
        user.setLastDay(today);
        userDao.update(user);
    }

    private void setComplete(int id, int num) {
        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(id);
        UserPlanDao userPlanDao = new UserPlanDao(EatActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(id + "" + user.getLastDay());
        EatTableDao eatTableDao = new EatTableDao(EatActivity.this);
        EatTable eatTable = eatTableDao.getEatTableById(userPlan.getRecipeId());
        switch (num) {
            case 1:
                userPlan.setFirstRecipeComplete(!userPlan.getFirstRecipeComplete());
                break;
            case 2:
                userPlan.setSecondRecipeComplete(!userPlan.getSecondRecipeComplete());
                break;
            case 3:
                userPlan.setThirdRecipeComplete(!userPlan.getThirdRecipeComplete());
                break;
        }
        userPlanDao.update(userPlan);
        setImageAndText(id);
    }

    private void detailDialog(int data, int recipenum) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(data);
        UserPlanDao userPlanDao = new UserPlanDao(EatActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(data + "" + user.getLastDay());
        EatTableDao eatTableDao = new EatTableDao(EatActivity.this);
        EatTable eatTable = eatTableDao.getEatTableById(userPlan.getRecipeId());
        dialog.setTitle("This is the details about your recipe:");
        switch (recipenum) {
            case 1:
                dialog.setMessage(eatTable.getRecipeOneContent());
                break;
            case 2:
                dialog.setMessage(eatTable.getRecipeTwoContent());
                break;
            case 3:
                dialog.setMessage(eatTable.getRecipeThreeContent());
                break;
            default:
                break;
        }
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EatActivity.this, "OK", Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setdayandremind, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee", 0);
        switch (item.getItemId()) {
            case R.id.firstday:
                updateToday(data, 1);
                break;
            case R.id.secondday:
                updateToday(data, 2);
                break;
            case R.id.thirdday:
                updateToday(data, 3);
                break;
            case R.id.forthday:
                updateToday(data, 4);
                break;
            case R.id.fifthday:
                updateToday(data, 5);
                break;
            case R.id.sixthday:
                updateToday(data, 6);
                break;
            case R.id.seventhday:
                updateToday(data, 7);
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

    private void setBottomBar(int data) {
        ImageButton bottom_eat = (ImageButton) findViewById(R.id.bottom_eat);
        ImageButton bottom_exercise = (ImageButton) findViewById(R.id.bottom_exercise);
        ImageButton bottom_settings = (ImageButton) findViewById(R.id.bottom_settings);
        ImageButton bottom_help = (ImageButton) findViewById(R.id.bottom_help);
        bottom_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(EatActivity.this, EatActivity.class);
                intent_toeatpage.putExtra("usee", data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(EatActivity.this, ExerciseActivity.class);
                intent_toexercisepage.putExtra("usee", data);
                startActivity(intent_toexercisepage);
            }
        });
        bottom_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(EatActivity.this, SettingsActivity.class);
                intent_toeatpage.putExtra("usee", data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(EatActivity.this, HelpActivity.class);
                intent_toexercisepage.putExtra("usee", data);
                startActivity(intent_toexercisepage);
            }
        });
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR,
                            Manifest.permission.READ_CALENDAR}, 1);
            return false;
        }
        return true;
    }

    private void setClock(int num) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(EatActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                switch (num) {
                    case 1:
                        addCalendarEvent(EatActivity.this, "It's time to eat your breakfast!", "Don't forget to eat your breakfast!", hourOfDay, minute);
                        Toast.makeText(EatActivity.this, "OK" + hourOfDay + minute, Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        addCalendarEvent(EatActivity.this, "It's time to eat your lunch!", "Don't forget to eat your lunch!", hourOfDay, minute);
                        Toast.makeText(EatActivity.this, "OK" + hourOfDay + minute, Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        addCalendarEvent(EatActivity.this, "It's time to eat your dinner!", "Don't forget to eat your dinner!", hourOfDay, minute);
                        Toast.makeText(EatActivity.this, "OK" + hourOfDay + minute, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}