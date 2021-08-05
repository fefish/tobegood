package com.example.tobegood.dao;


import android.content.Context;

import com.example.tobegood.DatabaseHelper;
import com.example.tobegood.bean.EatTable;


import com.example.tobegood.bean.User;
import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.ExerciseTable;
import com.example.tobegood.bean.UserPlan;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


public class EatTableDao {

    //上下文
    private Context mContext;
    //主键查询
    private Dao<EatTable,Integer>eatTableDao;
    //dao类
    private DatabaseHelper helper;

    public EatTableDao(Context context){
        this.mContext = context;
        //创建数据库
        helper = DatabaseHelper.getInstance(context);
        try{
            //操作dao
            eatTableDao = helper.getDao(EatTable.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void add(EatTable eatTable){
        try{
            eatTableDao.create(eatTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(EatTable eatTable){
        try{
            eatTableDao.update(eatTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(EatTable eatTable){
        try{
            eatTableDao.delete(eatTable);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<EatTable> listall(){
        try{
            return eatTableDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public EatTable getEatTableById(int id) {
        EatTable eatTable = null;
        try {
            eatTable = eatTableDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eatTable;
    }
}