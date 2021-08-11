package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;

import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.*;
import com.example.tobegood.bean.*;
import com.example.tobegood.bean.User;
import android.widget.Toast;

public class Register extends AppCompatActivity  {
    private boolean msex = false;
    private boolean mvegan = true;
    private boolean meatdisorder = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar();
        Button button_register_register = (Button) findViewById(R.id.Button_register_register);
        EditText edit_register_id=(EditText)findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText)findViewById(R.id.Edit_register_name);
        EditText edit_register_password=(EditText)findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText)findViewById(R.id.Edit_register_height);
        EditText edit_register_weight=(EditText)findViewById(R.id.Edit_register_weight);
        RadioGroup radioGroup_register_sex=(RadioGroup)findViewById(R.id.RadioGroup_register_sex);
        RadioGroup radioGroup_register_vegan=(RadioGroup)findViewById(R.id.RadioGroup_register_vegan);
        RadioGroup radioGroup_register_eatdisorder= (RadioGroup) findViewById(R.id.RadioGroup_register_eatdisorder);
        radioGroup_register_sex.check(R.id.Radiobutton_register_male);
        radioGroup_register_vegan.check(R.id.Radiobutton_register_vegan);
        radioGroup_register_eatdisorder.check(R.id.Radiobutton_register_eatdisorder);
        button_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup_register_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkId) {
                        switch (checkId){
                            case R.id.Radiobutton_register_male:
                                msex = true;
                                break;
                            case R.id.Radiobutton_register_female:
                                msex = false;
                                break;
                            default:
                                break; }}});
                radioGroup_register_vegan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkId) {
                        switch (checkId){
                            case R.id.Radiobutton_register_notvegan:
                                mvegan = true;
                                break;
                            case R.id.Radiobutton_register_vegan:
                                mvegan = false;
                                break;
                            default:
                                break; }}});
                radioGroup_register_eatdisorder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkId) {
                        switch (checkId){
                            case R.id.Radiobutton_register_eatdisorder:
                                meatdisorder = true;
                                break;
                            case R.id.Radiobutton_register_noeatdisorder:
                                meatdisorder = false;
                                break;
                            default:
                                break; }}});
                boolean buttons = false;
                if (TextUtils.isEmpty(edit_register_id.getText())||TextUtils.isEmpty(edit_register_name.getText())||TextUtils.isEmpty(edit_register_password.getText())||TextUtils.isEmpty(edit_register_height.getText())||TextUtils.isEmpty(edit_register_weight.getText())){
                    Toast.makeText(getApplicationContext(),"Please fill up all the information, thank you.",Toast.LENGTH_SHORT).show();
                }else {
                    UserDao userDao = new UserDao(Register.this);
                    User user = new User();
                    user.setId(Integer.parseInt(edit_register_id.getText().toString()));
                    user.setName(edit_register_name.getText().toString());
                    user.setPassword(edit_register_password.getText().toString());
                    user.setHeight(Integer.parseInt(edit_register_height.getText().toString()));
                    user.setWeight(Integer.parseInt(edit_register_weight.getText().toString()));
                    user.setSex(msex);
                    user.setVegan(mvegan);
                    user.setEatdisorder(meatdisorder);
                    user.setLastday(1);
                    userDao.add(user);

                    /*set userplan*/
                    UserPlanDao userPlanDao = new UserPlanDao(Register.this);
                    UserPlan userPlan1 = new UserPlan(user.getId() + "1", 1, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan1);
                    UserPlan userPlan2 = new UserPlan(user.getId() + "" + "2", 2, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan2);
                    UserPlan userPlan3 = new UserPlan(user.getId() + "" + "3", 3, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan3);
                    UserPlan userPlan4 = new UserPlan(user.getId() + "" + "4", 4, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan4);
                    UserPlan userPlan5 = new UserPlan(user.getId() + "" + "5", 5, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan5);
                    UserPlan userPlan6 = new UserPlan(user.getId() + "" + "6", 6, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan6);
                    UserPlan userPlan7 = new UserPlan(user.getId() + "" + "7", 7, false, false, false, 1, false, false, false);
                    userPlanDao.add(userPlan7);
                    Log.d("rerere", "onClick: " + userPlan1.toString());
                    Toast.makeText(getApplicationContext(), "register successful! Your information is" + user.toString(),
                            Toast.LENGTH_SHORT).show();
                    initialTable();
                    Intent intent_toMainPage = new Intent(Register.this, MainPage.class);
                    intent_toMainPage.putExtra("usee", user.getId());
                    startActivity(intent_toMainPage);
                }
            }
        });
    }

    private void initialTable(){
        EatTable eatTable1= new EatTable(1,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe002",
                "EatTable1Name2","EatTable1Name3","recipe003",
                "EatTable1Name3");
        EatTable eatTable2= new EatTable(2,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable3= new EatTable(3,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable4= new EatTable(4,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable5= new EatTable(5,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");
        EatTable eatTable6= new EatTable(6,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe001",
                "EatTable1Name2","EatTable1Name3","recipe001",
                "EatTable1Name3");
        EatTable eatTable7= new EatTable(7,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable8= new EatTable(8,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable9= new EatTable(9,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable10= new EatTable(10,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");
        EatTable eatTable11= new EatTable(11,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe001",
                "EatTable1Name2","EatTable1Name3","recipe001",
                "EatTable1Name3");
        EatTable eatTable12= new EatTable(12,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable13= new EatTable(13,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable14= new EatTable(14,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable15= new EatTable(15,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");
        EatTable eatTable16= new EatTable(16,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe001",
                "EatTable1Name2","EatTable1Name3","recipe001",
                "EatTable1Name3");
        EatTable eatTable17= new EatTable(17,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable18 = new EatTable(18,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable19= new EatTable(19,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable20= new EatTable(20,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");
        EatTable eatTable21= new EatTable(21,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe001",
                "EatTable1Name2","EatTable1Name3","recipe001",
                "EatTable1Name3");
        EatTable eatTable22= new EatTable(22,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable23= new EatTable(23,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable24= new EatTable(24,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable25= new EatTable(25,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");
        EatTable eatTable26= new EatTable(26,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe001",
                "EatTable1Name2","EatTable1Name3","recipe001",
                "EatTable1Name3");
        EatTable eatTable27= new EatTable(27,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable28= new EatTable(28,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable29= new EatTable(29,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable30= new EatTable(30,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");
        EatTable eatTable31= new EatTable(31,"EatTable1Name1","recipe001",
                "EatTable1Name1","EatTable1Name2","recipe001",
                "EatTable1Name2","EatTable1Name3","recipe001",
                "EatTable1Name3");
        EatTable eatTable32= new EatTable(32,"EatTable2Name1","recipe002",
                "EatTable2Name1","EatTable2Name2","recipe002",
                "EatTable2Name2","EatTable2Name3","recipe002",
                "EatTable2Name3");
        EatTable eatTable33= new EatTable(33,"EatTable3Name1","recipe003",
                "EatTable3Name1","EatTable3Name2","recipe003",
                "EatTable3Name2","EatTable3Name3","recipe003",
                "EatTable3Name3");
        EatTable eatTable34= new EatTable(34,"EatTable4Name1","recipe004",
                "EatTable4Name1","EatTable4Name2","recipe004",
                "EatTable4Name2","EatTable4Name3","recipe004",
                "EatTable4Name3");
        EatTable eatTable35= new EatTable(35,"EatTable5Name1","recipe005",
                "EatTable5Name1","EatTable5Name2","recipe005",
                "EatTable5Name2","EatTable5Name3","recipe005",
                "EatTable5Name3");

        EatTableDao eatTableDao = new EatTableDao(Register.this);
        EatTable eatTable111 = eatTableDao.getEatTableById(1);
        eatTableDao.add(eatTable1);
        eatTableDao.add(eatTable2);
        eatTableDao.add(eatTable3);
        eatTableDao.add(eatTable4);
        eatTableDao.add(eatTable5);
        eatTableDao.add(eatTable6);
        eatTableDao.add(eatTable7);
        eatTableDao.add(eatTable8);
        eatTableDao.add(eatTable9);
        eatTableDao.add(eatTable10);
        eatTableDao.add(eatTable11);
        eatTableDao.add(eatTable12);
        eatTableDao.add(eatTable13);
        eatTableDao.add(eatTable14);
        eatTableDao.add(eatTable15);
        eatTableDao.add(eatTable16);
        eatTableDao.add(eatTable17);
        eatTableDao.add(eatTable18);
        eatTableDao.add(eatTable19);
        eatTableDao.add(eatTable20);
        eatTableDao.add(eatTable21);
        eatTableDao.add(eatTable22);
        eatTableDao.add(eatTable23);
        eatTableDao.add(eatTable24);
        eatTableDao.add(eatTable25);
        eatTableDao.add(eatTable26);
        eatTableDao.add(eatTable27);
        eatTableDao.add(eatTable28);
        eatTableDao.add(eatTable29);
        eatTableDao.add(eatTable30);
        eatTableDao.add(eatTable31);
        eatTableDao.add(eatTable32);
        eatTableDao.add(eatTable33);
        eatTableDao.add(eatTable34);
        eatTableDao.add(eatTable35);
        EatTable eatTable222 = eatTableDao.getEatTableById(1);
        // Log.d("222", eatTable222.getRecipeOnePic());
        ExerciseTable exerciseTable1= new ExerciseTable(1,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable2= new ExerciseTable(2,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable3= new ExerciseTable(3,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable4= new ExerciseTable(4,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable5= new ExerciseTable(5,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable6= new ExerciseTable(6,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable7= new ExerciseTable(7,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable8= new ExerciseTable(8,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable9= new ExerciseTable(9,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable10= new ExerciseTable(10,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable11= new ExerciseTable(11,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable12= new ExerciseTable(12,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable13= new ExerciseTable(13,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable14= new ExerciseTable(14,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable15= new ExerciseTable(15,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable16= new ExerciseTable(16,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable17= new ExerciseTable(17,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable18= new ExerciseTable(18,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable19= new ExerciseTable(19,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable20= new ExerciseTable(20,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable21= new ExerciseTable(21,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable22= new ExerciseTable(22,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable23= new ExerciseTable(23,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable24= new ExerciseTable(24,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable25= new ExerciseTable(25,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable26= new ExerciseTable(26,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable27= new ExerciseTable(27,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable28= new ExerciseTable(28,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable29= new ExerciseTable(29,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable30= new ExerciseTable(30,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTable exerciseTable31= new ExerciseTable(31,"ExerciseTable1Name1","recipe001",
                "ExerciseTable1Name1","ExerciseTable1Name2","recipe001",
                "ExerciseTable1Name2","ExerciseTable1Name3","recipe001",
                "ExerciseTable1Name3");
        ExerciseTable exerciseTable32= new ExerciseTable(32,"ExerciseTable2Name1","recipe002",
                "ExerciseTable2Name1","ExerciseTable2Name2","recipe002",
                "ExerciseTable2Name2","ExerciseTable2Name3","recipe002",
                "EatTable2Name3");
        ExerciseTable exerciseTable33= new ExerciseTable(33,"ExerciseTable3Name1","recipe003",
                "ExerciseTable3Name1","ExerciseTable3Name2","recipe003",
                "ExerciseTable3Name2","ExerciseTable3Name3","recipe003",
                "ExerciseTable3Name3");
        ExerciseTable exerciseTable34= new ExerciseTable(34,"ExerciseTable4Name1","recipe004",
                "ExerciseTable4Name1","ExerciseTable4Name2","recipe004",
                "ExerciseTable4Name2","ExerciseTable4Name3","recipe004",
                "ExerciseTable4Name3");
        ExerciseTable exerciseTable35= new ExerciseTable(35,"ExerciseTable5Name1","recipe005",
                "ExerciseTable5Name1","ExerciseTable5Name2","recipe005",
                "ExerciseTable5Name2","ExerciseTable5Name3","recipe005",
                "ExerciseTable5Name3");
        ExerciseTableDao exerciseTableDao = new ExerciseTableDao(Register.this);
        exerciseTableDao.add(exerciseTable1);
        exerciseTableDao.add(exerciseTable2);
        exerciseTableDao.add(exerciseTable3);
        exerciseTableDao.add(exerciseTable4);
        exerciseTableDao.add(exerciseTable5);
        exerciseTableDao.add(exerciseTable6);
        exerciseTableDao.add(exerciseTable7);
        exerciseTableDao.add(exerciseTable8);
        exerciseTableDao.add(exerciseTable9);
        exerciseTableDao.add(exerciseTable10);
        exerciseTableDao.add(exerciseTable11);
        exerciseTableDao.add(exerciseTable12);
        exerciseTableDao.add(exerciseTable13);
        exerciseTableDao.add(exerciseTable14);
        exerciseTableDao.add(exerciseTable15);
        exerciseTableDao.add(exerciseTable16);
        exerciseTableDao.add(exerciseTable17);
        exerciseTableDao.add(exerciseTable18);
        exerciseTableDao.add(exerciseTable19);
        exerciseTableDao.add(exerciseTable20);
        exerciseTableDao.add(exerciseTable21);
        exerciseTableDao.add(exerciseTable22);
        exerciseTableDao.add(exerciseTable23);
        exerciseTableDao.add(exerciseTable24);
        exerciseTableDao.add(exerciseTable25);
        exerciseTableDao.add(exerciseTable26);
        exerciseTableDao.add(exerciseTable27);
        exerciseTableDao.add(exerciseTable28);
        exerciseTableDao.add(exerciseTable29);
        exerciseTableDao.add(exerciseTable30);
        exerciseTableDao.add(exerciseTable31);
        exerciseTableDao.add(exerciseTable32);
        exerciseTableDao.add(exerciseTable33);
        exerciseTableDao.add(exerciseTable34);
        exerciseTableDao.add(exerciseTable35);
    }

    private  void setToolbar(){
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
        toolbar.setTitle("tobegood");
        toolbar.setSubtitle("Welcome! This is Register Page.");
        toolbar.setTitleTextColor(getResources().getColor(R.color.picturebrown));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.fontblue));
    }
}


