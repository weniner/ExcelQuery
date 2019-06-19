package com.example.query;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.query.gen.DaoMaster;
import com.example.query.gen.DaoSession;


/**
 * 数据库创建初始化
 * @Time: 2018/12/27
 **/
public class App extends Application {
    public static App instances;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "User", null);//创建名为User的数据库
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    public static App getInstances() {
        return instances;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
