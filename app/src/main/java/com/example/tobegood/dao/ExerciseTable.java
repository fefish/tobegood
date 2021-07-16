package com.example.tobegood.dao;

import android.content.Context;

import com.example.tobegood.DatabaseHelper;
import com.example.tobegood.bean.EatTable;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class ExerciseTable {
    //上下文
    private Context mContext;
    //主键查询
    private Dao<ExerciseTable,Integer>exerciseTableDao;
    //dao类
    private DatabaseHelper helper;

    public ExerciseTable (Context context){
        this.mContext = context;
        //创建数据库
        helper = DatabaseHelper.getInstance(context);
        try{
            //操作dao
            exerciseTableDao = helper.getDao(ExerciseTable.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void add(ExerciseTable exerciseTable){
        try{
            exerciseTableDao.create(exerciseTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(ExerciseTable exerciseTable){
        try{
            exerciseTableDao.update(exerciseTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(ExerciseTable exerciseTable){
        try{
            exerciseTableDao.delete(exerciseTable);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<ExerciseTable> listall(){
        try{
            return exerciseTableDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
