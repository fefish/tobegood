package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.tobegood.dao.UserDao;
import com.example.tobegood.bean.User;

import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private boolean mySex;
    private boolean myVegan;
    private boolean myEatDisorder;
    private int myPurpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button_register_register = (Button) findViewById(R.id.Button_register_register);
        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee", 0);
        setToolbar(data);
        setBottomBar(data);
        initialPage(data);
        RadioGroup radioGroup_register_sex = (RadioGroup) findViewById(R.id.RadioGroup_register_sex);
        RadioGroup radioGroup_register_vegan = (RadioGroup) findViewById(R.id.RadioGroup_register_vegan);
        RadioGroup radioGroup_register_eatdisorder = (RadioGroup) findViewById(R.id.RadioGroup_register_eatdisorder);
        RadioGroup radioGroup_register_pupose = (RadioGroup) findViewById(R.id.RadioGroup_register_purpose);
        radioGroup_register_pupose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_purpose1:
                        myPurpose = 1;
                        break;
                    case R.id.Radiobutton_register_purpose2:
                        myPurpose = 2;
                        break;
                    case R.id.Radiobutton_register_purpose3:
                        myPurpose = 3;
                        break;
                }
            }
        });
        radioGroup_register_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_male:
                        mySex = true;
                        break;
                    case R.id.Radiobutton_register_female:
                        mySex = false;
                        break;
                    default:
                        break;
                }
            }
        });
        radioGroup_register_vegan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_vegan:
                        myVegan = true;
                        break;
                    case R.id.Radiobutton_register_notvegan:
                        myVegan = false;
                        break;
                    default:
                        break;
                }
            }
        });
        radioGroup_register_eatdisorder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.Radiobutton_register_eatdisorder:
                        myEatDisorder = true;
                        break;
                    case R.id.Radiobutton_register_noeatdisorder:
                        myEatDisorder = false;
                        break;
                    default:
                        break;
                }
            }
        });
        button_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao userDao = new UserDao(SettingsActivity.this);
                User user = getUser();
                if (findEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill up all the information, thank you.", Toast.LENGTH_SHORT).show();
                } else {
                    userDao.update(user);
                    Toast.makeText(getApplicationContext(), "You have updated your information! Now your information is " + user.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        UserDao userDao = new UserDao(SettingsActivity.this);
        User user = userDao.getUserById(data);
        toolbar.setTitle("tobegood");
        toolbar.setSubtitle(user.getName() + ", you can change your details.");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
    }

    private void initialPage(int data) {
        UserDao userDao = new UserDao(SettingsActivity.this);
        User user = userDao.getUserById(data);
        EditText edit_register_id = (EditText) findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText) findViewById(R.id.Edit_register_name);
        EditText edit_register_password = (EditText) findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText) findViewById(R.id.Edit_register_height);
        EditText edit_register_weight = (EditText) findViewById(R.id.Edit_register_weight);
        EditText edit_register_contactnumber = (EditText) findViewById(R.id.Edit_register_contactnumber);
        RadioGroup radioGroup_register_sex = (RadioGroup) findViewById(R.id.RadioGroup_register_sex);
        RadioGroup radioGroup_register_vegan = (RadioGroup) findViewById(R.id.RadioGroup_register_vegan);
        RadioGroup radioGroup_register_eatdisorder = (RadioGroup) findViewById(R.id.RadioGroup_register_eatdisorder);
        RadioGroup radioGroup_register_purpose = (RadioGroup) findViewById(R.id.RadioGroup_register_purpose);
        edit_register_id.setText(user.getId() + "");
        edit_register_password.setText(user.getPassword());
        edit_register_name.setText(user.getName());
        edit_register_height.setText((int) user.getHeight() + "");
        edit_register_weight.setText((int) user.getWeight() + "");
        edit_register_contactnumber.setText(user.getEmergencyNumber());
        if (user.isSex()) {
            radioGroup_register_sex.check(R.id.Radiobutton_register_male);
        } else {
            radioGroup_register_sex.check(R.id.Radiobutton_register_female);
        }
        if (user.isVegan()) {
            radioGroup_register_vegan.check(R.id.Radiobutton_register_vegan);
        } else {
            radioGroup_register_vegan.check(R.id.Radiobutton_register_notvegan);
        }
        if (user.isEatdisorder()) {
            radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_eatdisorder);
        } else {
            radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_noeatdisorder);
        }
        if (user.getPurpose()==1) {
            radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_purpose1);
        } else if(user.getPurpose()==2) {
            radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_purpose2);
        }else if(user.getPurpose()==3){
            radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_purpose2);
        }
    }

    private User getUser() {
        EditText edit_register_id = (EditText) findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText) findViewById(R.id.Edit_register_name);
        EditText edit_register_password = (EditText) findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText) findViewById(R.id.Edit_register_height);
        EditText edit_register_weight = (EditText) findViewById(R.id.Edit_register_weight);
        EditText edit_register_contactnumber = (EditText) findViewById(R.id.Edit_register_contactnumber);
        User user = new User();
        user.setId(Integer.parseInt(edit_register_id.getText().toString()));
        user.setName(edit_register_name.getText().toString());
        user.setPassword(edit_register_password.getText().toString());
        user.setHeight(Integer.parseInt(edit_register_height.getText().toString()));
        user.setWeight(Integer.parseInt(edit_register_weight.getText().toString()));
        user.setSex(mySex);
        user.setVegan(myVegan);
        user.setEatdisorder(myEatDisorder);
        user.setLastDay(1);
        user.setEmergencyNumber(edit_register_contactnumber.getText().toString());
        return user;
    }

    private void setUserPlan(int id) {
        //xie
    }

    public boolean findEmpty() {
        EditText edit_register_id = (EditText) findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText) findViewById(R.id.Edit_register_name);
        EditText edit_register_password = (EditText) findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText) findViewById(R.id.Edit_register_height);
        EditText edit_register_weight = (EditText) findViewById(R.id.Edit_register_weight);
        boolean findempty = TextUtils.isEmpty(edit_register_id.getText()) || TextUtils.isEmpty(edit_register_name.getText()) || TextUtils.isEmpty(edit_register_password.getText()) || TextUtils.isEmpty(edit_register_height.getText()) || TextUtils.isEmpty(edit_register_weight.getText());
        return findempty;
    }

    private void setBottomBar(int data) {
        ImageButton bottom_eat = (ImageButton) findViewById(R.id.bottom_eat);
        ImageButton bottom_exercise = (ImageButton) findViewById(R.id.bottom_exercise);
        ImageButton bottom_settings = (ImageButton) findViewById(R.id.bottom_settings);
        ImageButton bottom_help = (ImageButton) findViewById(R.id.bottom_help);
        bottom_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(SettingsActivity.this, EatActivity.class);
                intent_toeatpage.putExtra("usee", data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(SettingsActivity.this, ExerciseActivity.class);
                intent_toexercisepage.putExtra("usee", data);
                startActivity(intent_toexercisepage);
            }
        });
        bottom_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toeatpage = new Intent(SettingsActivity.this, SettingsActivity.class);
                intent_toeatpage.putExtra("usee", data);
                startActivity(intent_toeatpage);
            }
        });
        bottom_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toexercisepage = new Intent(SettingsActivity.this, HelpActivity.class);
                intent_toexercisepage.putExtra("usee", data);
                startActivity(intent_toexercisepage);
            }
        });
    }
}


