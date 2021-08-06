package com.example.tobegood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.User;
import com.example.tobegood.bean.UserPlan;
import com.example.tobegood.dao.EatTableDao;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.UserPlanDao;

public class EatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatactivity);
        ImageView Image_eat_recipe1 = (ImageView) findViewById(R.id.Image_eat_recipe1);
        ImageView Image_eat_recipe2 = (ImageView) findViewById(R.id.Image_eat_recipe2);
        ImageView Image_eat_recipe3 = (ImageView) findViewById(R.id.Image_eat_recipe3);
        Button Button_eat_recipe1_detail = (Button) findViewById(R.id.Button_eat_recipe1_detail);
        Button Button_eat_recipe2_detail = (Button) findViewById(R.id.Button_eat_recipe2_detail);
        Button Button_eat_recipe3_detail = (Button) findViewById(R.id.Button_eat_recipe3_detail);
        Button Button_eat_recipe1_complete = (Button) findViewById(R.id.Button_eat_recipe1_complete);
        Button Button_eat_recipe2_complete = (Button) findViewById(R.id.Button_eat_recipe2_complete);
        Button Button_eat_recipe3_complete = (Button) findViewById(R.id.Button_eat_recipe3_complete);

        Intent intent_getfrompre = getIntent();
        int data = intent_getfrompre.getIntExtra("usee",0);
        Toast.makeText(getApplicationContext(),"You have been to the main page! Your id is"+data,
                Toast.LENGTH_SHORT).show();
        UserDao userDao = new UserDao(EatActivity.this);
        User user = userDao.getUserById(data);
        Toast.makeText(getApplicationContext(),"You have been to the main page! Your id is"+user.toString(),
                Toast.LENGTH_SHORT).show();
        UserPlanDao userPlanDao = new UserPlanDao(EatActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(data);
        Log.d("eee", "onCreate: "+userPlan.toString());
        userPlan.getId();
        EatTableDao eatTableDao = new EatTableDao(EatActivity.this);
        EatTable eatTable = eatTableDao.getEatTableById(userPlan.getRecipeId());
        if (eatTable.getRecipeOnePic().equals("recipe001")){
            Image_eat_recipe1.setImageResource(R.drawable.recipe001);
        }
        /*Button_eat_recipe1_detail.setOnClickListener(detailListener);
        Button_eat_recipe2_detail.setOnClickListener(detailListener);
        Button_eat_recipe3_detail.setOnClickListener(detailListener);
        View.OnClickListener detailListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.Button_eat_recipe1_detail:
                        detailDialog(eatTable,1);
                        break;

                }
            }
        };*/
        Button_eat_recipe1_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(eatTable,1);
            }});
        Button_eat_recipe2_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(eatTable,2);
            }});
        Button_eat_recipe3_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(eatTable,3);
            }});
        Button_eat_recipe1_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_eat_recipe1.setImageResource(R.drawable.complete);
            }});
        Button_eat_recipe2_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_eat_recipe2.setImageResource(R.drawable.complete);
            }});
        Button_eat_recipe3_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image_eat_recipe3.setImageResource(R.drawable.complete);
            }});
/*        String resource1 = "R.drawable."+eatTable.getRecipeOnePic();
        Image_eat_recipe1.setImageResource(resource1);*/
        //Toast.makeText(getApplicationContext(),"Your id is"+userPlan.getId(),
        //        Toast.LENGTH_SHORT).show();
        //int recipeId = userPlan.getRecipeId();
/*        EatTableDao eatTableDao = new EatTableDao(EatActivity.this);
        EatTable eatTable = eatTableDao.getEatTableById(yourid);
        Toast.makeText(getApplicationContext(),"You have been find the recipe! Your id is"+yourid,
                Toast.LENGTH_SHORT).show();*/

    }


    private void detailDialog(EatTable eatTable,int recipenum){

        AlertDialog dialog = new AlertDialog.Builder (this).create ();
        dialog.setTitle ("This is the details about your recipe:");
        switch (recipenum) {
            case 1:
                dialog.setMessage (eatTable.getRecipeOneContent());
                break;
            case 2:
                dialog.setMessage (eatTable.getRecipeTwoContent());
                break;
            case 3:
                dialog.setMessage (eatTable.getRecipeThreeContent());
                break;
            default:
                break;
        }
        dialog.setButton (DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText (EatActivity.this,"OK",Toast.LENGTH_LONG).show ();
            }
        });
        /*dialog2.setButton (DialogInterface.BUTTON_NEGATIVE, "3分", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText (EatActivity.this,"3分",Toast.LENGTH_LONG).show ();
            }
        });*/
        //一定要调用show（）方法，否则对话框不会显示
        dialog.show ();
    }
}