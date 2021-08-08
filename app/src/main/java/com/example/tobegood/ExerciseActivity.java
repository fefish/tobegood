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
import com.example.tobegood.bean.ExerciseTable;
import com.example.tobegood.dao.EatTableDao;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.dao.UserPlanDao;
import com.example.tobegood.dao.ExerciseTableDao;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseactivity);
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

        UserPlanDao userPlanDao = new UserPlanDao(ExerciseActivity.this);
        UserPlan userPlan = userPlanDao.getUserPlanById(data);

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