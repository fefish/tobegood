package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;
import com.example.tobegood.Dao.userDao;
import com.example.tobegood.bean.user;

public class register extends AppCompatActivity  {

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
        String midstr = edit_register_id.getText().toString();
        String mnamestr = edit_register_name.getText().toString();
        String mpasswordstr = edit_register_password.getText().toString();
        String mheightstr = edit_register_height.getText().toString();
        String mweightstr = edit_register_weight.getText().toString();
        Integer mid = Integer.parseInt(midstr);
        //Integer mname = Integer.parseInt(mnamestr);
        //Integer mpassword = Integer.parseInt(mpasswordstr);
        Integer mheight = Integer.parseInt(mheightstr);
        Integer mweight = Integer.parseInt(mweightstr);
        //button_register_register.setOnClickListener(this);
        boolean msex = false;
        boolean mvegan = true;
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
        button_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao muserDao= new userDao(this);
                user muser = new user();
                muser.setId(mid);
                muser.setName(mnamestr);
                muser.setPassword(mpasswordstr);
                muser.setHeight(mheight);
                muser.setWeight(mweight);
                muser.setSex(msex);
                muser.setVegan(mvegan);
                muserDao.adduser(muser); }
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
