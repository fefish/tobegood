package com.example.tobegood.Dao;

import android.content.Context;

import com.example.tobegood.DatabaseHelper;
import com.example.tobegood.bean.User;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


public class UserDao {
    //上下文
    private Context mContext;
    //主键查询
    private Dao<User,Integer>userDao;
    //dao类
    private DatabaseHelper helper;

    public UserDao(Context context){
        this.mContext = context;
        //创建数据库
        helper = DatabaseHelper.getInstance(context);
        try{
            //操作dao
            userDao = helper.getDao(User.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void add(User user){
        try{
            userDao.create(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(User user){
        try{
            userDao.update(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(User user){
        try{
            userDao.delete(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> listall(){
        try{
            return userDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
