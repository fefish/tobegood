package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;

import com.example.tobegood.Dao.UserDao;
import com.example.tobegood.bean.User;

public class register extends AppCompatActivity  {
    private boolean msex = false;
    private boolean mvegan = true;
    String midstr;
    String mnamestr;
    String mpasswordstr;
    String mheightstr;
    String mweightstr;
    Integer mid;
    //Integer mname;
    //Integer mpassword;
    Integer mheight;
    Integer mweight;
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
                midstr = edit_register_id.getText().toString();
                mnamestr = edit_register_name.getText().toString();
                mpasswordstr = edit_register_password.getText().toString();
                mheightstr = edit_register_height.getText().toString();
                mweightstr = edit_register_weight.getText().toString();
                mid = Integer.parseInt(midstr);
                //Integer mname = Integer.parseInt(mnamestr);
                //Integer mpassword = Integer.parseInt(mpasswordstr);
                mheight = Integer.parseInt(mheightstr);
                mweight = Integer.parseInt(mweightstr);
                //button_register_register.setOnClickListener(this);
                UserDao muserDao= new UserDao(register.this);
                User muser = new User();
                muser.setId(mid);
                muser.setName(mnamestr);
                muser.setPassword(mpasswordstr);
                muser.setHeight(mheight);
                muser.setWeight(mweight);
                muser.setSex(msex);
                muser.setVegan(mvegan);
                muserDao.add(muser);
                muserDao.listall();
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
