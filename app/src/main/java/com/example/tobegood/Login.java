package com.example.tobegood;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tobegood.bean.User;
import com.example.tobegood.dao.UserDao;
import com.example.tobegood.DatabaseHelper;

public class Login extends AppCompatActivity {
    @Override//普通菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //返回true代表普通菜单显示
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //以下三行是修改回退按钮为白色的逻辑
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

        EditText edit_login_id=(EditText)findViewById(R.id.Edit_login_id);
        EditText edit_login_password=(EditText)findViewById(R.id.Edit_login_password);
        Button button_login_login=(Button)findViewById(R.id.Button_login_login);

        button_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginId = edit_login_id.getText().toString();
                String password = edit_login_password.getText().toString();
                int id = Integer.parseInt(loginId);
                UserDao userDao = new UserDao(Login.this);
                User user = userDao.getUserById(id);
                if(userDao.getUserById(id) == null){
                    Toast.makeText (Login.this,"Please register first!",Toast.LENGTH_LONG).show ();
                }else if (user.getPassword().equals(password)) {
                    Intent intent_toMainPage = new Intent(Login.this, MainPage.class);
                    intent_toMainPage.putExtra("usee",user.getId());
                    startActivity(intent_toMainPage);
                }else{
                    Toast.makeText (Login.this,"Wrong id or password! Please retry.",Toast.LENGTH_LONG).show ();
                }
            }
        });

    }
}