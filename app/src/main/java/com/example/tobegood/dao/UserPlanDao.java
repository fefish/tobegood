package com.example.tobegood.dao;

import android.content.Context;

import com.example.tobegood.DatabaseHelper;
import com.example.tobegood.bean.User;
import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.ExerciseTable;
import com.example.tobegood.bean.UserPlan;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class UserPlanDao {
    //上下文
    private Context mContext;
    //主键查询
    private Dao<UserPlan,String>userPlanDao;
    //dao类
    private DatabaseHelper helper;

    public UserPlanDao(Context context){
        this.mContext = context;
        //创建数据库
        helper = DatabaseHelper.getInstance(context);
        try{
            //操作dao
            userPlanDao = helper.getDao(UserPlan.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void add(UserPlan userPlan){
        try{
            userPlanDao.create(userPlan);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(UserPlan userPlan){
        try{
            userPlanDao.update(userPlan);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(UserPlan userPlan){
        try{
            userPlanDao.delete(userPlan);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<UserPlan> listall(){
        try{
            return userPlanDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    //

    public UserPlan getUserPlanById(String id){
        UserPlan userPlan = null;
        try {
            userPlan = userPlanDao.queryForId(id);
            return  userPlan;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userPlan;
    }

    public UserPlan getUserPlanByIdAndToday(int id,int today){
        UserPlan userPlan = null;
        try {
            userPlan = userPlanDao.queryBuilder().where().eq("id",id).and().eq("today",today).queryForFirst();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userPlan;
    }

}
