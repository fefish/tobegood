package com.example.tobegood.Dao;

import android.content.Context;
import android.view.View;

import com.example.tobegood.DatabaseHelper;
import com.example.tobegood.bean.user;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;



public class userDao {
    //上下文
    private Context mContext;
    //主键查询
    private Dao<user,Integer>userDao;
    //dao类
    private DatabaseHelper helper;

    public userDao(View.OnClickListener mContext){
        this.mContext = mContext;
        //创建数据库
        helper = DatabaseHelper.getInstance(mContext);
        try{
            //操作dao
            userDao = helper.getDao(user.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void adduser(user muser){
        try{
            userDao.create(muser);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
