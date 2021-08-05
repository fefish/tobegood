package com.example.tobegood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import com.example.tobegood.bean.EatTable;
import com.example.tobegood.bean.ExerciseTable;
import com.example.tobegood.bean.UserPlan;
import com.example.tobegood.dao.EatTableDao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
//这个是不是得自己写？
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.example.tobegood.bean.User;
//有别的表也写在这里

//ORMlite需要有一个继承自OrmLiteSqliteOpenHelper的类，来完成数据的创建和升级
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    //数据库的名称
    private static final String TABLE_NAME = "tobegood.db";
    //数据库的版本号
    private static int DATABASE_VERSION = 1;
    //存放dao，效果类似于只要有了指定的String，就能找到对应的Dao。
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private Dao<User, Integer> userDao;
    private Dao<EatTable,Integer>eatTableDao;
    private Dao<ExerciseTable,Integer>exerciseTableDao;
    private Dao<UserPlan,Integer>userPlanDao;

    //构造函数，private这样该类就不会被实例化
    private DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }
    /**
     * 参数说明：
     * context：上下文。
     * databaseName： 数据库名。
     * factory： 游标实例，多数时候设置成NULL。
     * databaseVersion：数据库版本，当数据库版本升高时，会调用onUpgrade（）方法。
     */

    //检测到异常就创建一个表，这是onCreate
    /**try中的创建表语句异常的时候，执行catch中的语句。
     * 即将catch后面的Exception e初始化，即实例化，e是此对象的名称（将异常赋予实例e）
     * 然后用printStackTrace就是在命令行打印异常信息在程序里出错的位置和原因。
     */

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource){
        try{
            //String sqlpath= "app/assets/EatTable_info.sql";
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource,EatTable.class);
            TableUtils.createTableIfNotExists(connectionSource,ExerciseTable.class);
            TableUtils.createTableIfNotExists(connectionSource, UserPlan.class);

            /*File file = new File(System.getProperty("user.dir"));
            Log.d("1111", String.valueOf(file));*/
/*            String sqlStr = readFileByLines(sqlpath);
            database.execSQL(sqlStr);*/
            //TableUtils.createTable(connectionSource,EatTable_Info.sql);
            //如果有别的表，写在这里应该
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
   /* private String readFileByLines(String filePath) throws Exception {
        StringBuffer str = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath), "UTF-8"));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);

                str = str.append(" " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return str.toString();
    }
*/


    /**
     * 在该方法中进行创建表操作
     * 使用OrmLite 框架的 TableUtils.createTable进行创建表操作。
     *eg.
     */

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource,EatTable.class,true);
            TableUtils.dropTable(connectionSource,ExerciseTable.class,true);
            TableUtils.dropTable(connectionSource,UserPlan.class,true);
            //TableUtils.dropTable(connectionSource, Article.class, true);
            //TableUtils.dropTable(connectionSource, Student.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param mcontext
     * @return
     */


    public static synchronized DatabaseHelper getInstance(Context mcontext){
        mcontext = mcontext.getApplicationContext();
        if(instance==null){
            synchronized (DatabaseHelper.class){
                if(instance == null)
                    instance = new DatabaseHelper(mcontext);
            }
        }
        return instance;
    }

    //获得数据库的访问对象

    public synchronized Dao getDao(Class clazz) throws SQLException{
        Dao dao = null;
        String className = clazz.getCanonicalName();

        if(daos.containsKey(className)){
            dao=daos.get(className);
        }
        if(dao == null){
            dao = super.getDao(clazz);
            daos.put(className,dao);
        }
        return dao;
    }


    public Dao<User, Integer> getUserDao() throws SQLException
    {
        if (userDao == null)
        {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<EatTable, Integer> getEatTableDao() throws SQLException
    {
        if (eatTableDao == null)
        {
            eatTableDao = getDao(EatTable.class);
        }
        return eatTableDao;
    }

    public Dao<ExerciseTable, Integer> getExerciseTableDao() throws SQLException
    {
        if (exerciseTableDao == null)
        {
            exerciseTableDao = getDao(ExerciseTable.class);
        }
        return exerciseTableDao;
    }

    public Dao<UserPlan, Integer> getUserPlanDao() throws SQLException
    {
        if (userPlanDao == null)
        {
            userPlanDao = getDao(UserPlan.class);
        }
        return userPlanDao;
    }
    /**
     * 释放资源
     */


    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }


}
