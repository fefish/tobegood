package com.example.tobegood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tobegood.Dao.UserDao;
import com.example.tobegood.bean.User;
import android.widget.Toast;

public class register extends AppCompatActivity  {
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
                UserDao userDao= new UserDao(register.this);
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
                Toast.makeText(getApplicationContext(),user.toString(),
                        Toast.LENGTH_SHORT).show();
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
