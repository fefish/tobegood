package com.example.tobegood;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.tobegood.bean.User;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.DatabaseHelper;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edit_login_id=(EditText)findViewById(R.id.Edit_login_id);
        EditText edit_login_password=(EditText)findViewById(R.id.Edit_login_password);
        Button button_login_login=(Button)findViewById(R.id.Button_login_login);

        button_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginId = edit_login_id.getText().toString();
                String loginPassword = edit_login_password.getText().toString();
                int id = Integer.parseInt(loginId);
                UserDao userDao = new UserDao(Login.this);
                User user = userDao.getUserById(id);
                String password = user.getPassword();
                if (password.equals(loginPassword)) {
                    Intent intent_toMainPage = new Intent(Login.this, MainPage.class);
                    startActivity(intent_toMainPage);
                }
            }
        });

    }
}