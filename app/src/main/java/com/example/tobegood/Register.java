package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
    /**
    final String midstr = null;
    final String mnamestr = null;
    final String mpasswordstr = null;
    final String mheightstr = null;
    final String mweightstr = null;
    final Integer mid = 0;
    //Integer mname;
    //Integer mpassword;
    final Integer mheight = 0;
    final Integer mweight = 0;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button_register_register = (Button) findViewById(R.id.Button_register_register);
        EditText edit_register_id=(EditText)findViewById(R.id.Edit_register_id);
        EditText edit_register_name = (EditText)findViewById(R.id.Edit_register_name);
        EditText edit_register_password=(EditText)findViewById(R.id.Edit_register_password);
        EditText edit_register_height = (EditText)findViewById(R.id.Edit_register_height);
        EditText edit_register_weight=(EditText)findViewById(R.id.Edit_register_weight);
        RadioGroup radioGroup_register_sex=(RadioGroup)findViewById(R.id.RadioGroup_register_sex);
        RadioGroup radioGroup_register_vegan=(RadioGroup)findViewById(R.id.RadioGroup_register_vegan);
        RadioGroup radioGroup_register_eatdisorder= (RadioGroup) findViewById(R.id.RadioGroup_register_eatdisorder);
        button_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //button_register_register.setOnClickListener(this);
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
                //DatabaseHelper helper = DatabaseHelper.getInstance();
                UserDao userDao= new UserDao(Register.this);
                User user = new User();
                final String midstr = edit_register_id.getText().toString();
                final String mnamestr = edit_register_name.getText().toString();
                final String mpasswordstr = edit_register_password.getText().toString();
                final String mheightstr = edit_register_height.getText().toString();
                final String mweightstr = edit_register_weight.getText().toString();
                final Integer mid = Integer.parseInt(midstr);
                //Integer mname = Integer.parseInt(mnamestr);
                //Integer mpassword = Integer.parseInt(mpasswordstr);
                final Integer mheight = Integer.parseInt(mheightstr);
                final Integer mweight = Integer.parseInt(mweightstr);
                user.setId(mid);
                user.setName(mnamestr);
                user.setPassword(mpasswordstr);
                user.setHeight(mheight);
                user.setWeight(mweight);
                user.setSex(msex);
                user.setVegan(mvegan);
                user.setEatdisorder(meatdisorder);
                userDao.add(user);
                userDao.listall();
               /* if (user.getWeight()/(user.getHeight()*user.getHeight())>10){
                    UserPlanDao userPlanDao = new UserPlanDao(Register.this);
                    UserPlan userPlan = new UserPlan(user.getId(),1,false,false,false,1,false,false,false);
                }*/
                UserPlanDao userPlanDao = new UserPlanDao(Register.this);
                UserPlan userPlan= new UserPlan(user.getId(),1,false,false,false,1,false,false,false);
                userPlanDao.add(userPlan);
                Log.d("rerere", "onClick: "+userPlan.toString());
                Toast.makeText(getApplicationContext(),"register successful! Your information is"+user.toString(),
                        Toast.LENGTH_SHORT).show();
                EatTable eatTable1= new EatTable(1,"EatTable1Name1","recipe001",
                        "EatTable1Name1","EatTable1Name2","recipe001",
                        "EatTable1Name2","EatTable1Name3","recipe001",
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
                EatTableDao eatTableDao = new EatTableDao(Register.this);
                EatTable eatTable111 = eatTableDao.getEatTableById(1);
                eatTableDao.add(eatTable1);
                eatTableDao.add(eatTable2);
                eatTableDao.add(eatTable3);
                eatTableDao.add(eatTable4);
                eatTableDao.add(eatTable5);
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
                ExerciseTableDao exerciseTableDao = new ExerciseTableDao(Register.this);
                exerciseTableDao.add(exerciseTable1);
                exerciseTableDao.add(exerciseTable2);
                exerciseTableDao.add(exerciseTable3);
                exerciseTableDao.add(exerciseTable4);
                exerciseTableDao.add(exerciseTable5);

                Intent intent_toMainPage = new Intent(Register.this,MainPage.class);
                intent_toMainPage.putExtra("usee",user.getId());
                //startActivityForResult(intent_toMainPage, 1);
                //Intent intent_toMainPage = new Intent(Register.this, MainPage.class);
                startActivity(intent_toMainPage);
            }
        });
    }
}
/**
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Button_register_register:
                userDao muserDao= new userDao(this);
                user muser = new user();
                muser.setId(mid);
                luoji
                break;
            default:
                break;
        }

    }
 */
