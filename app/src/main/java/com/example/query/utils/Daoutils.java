package com.example.query.utils;

import android.database.Cursor;
import android.text.TextUtils;

import com.example.query.App;
import com.example.query.gen.DaoSession;
import com.example.query.gen.UserBeanDao;
import com.example.query.model.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time: 2019/1/2
 **/
public class    Daoutils {
    /**
     * 单条插入
     *
     * @param userBean userBean
     */
    public static void inserCountry(UserBean userBean) {
        App.getInstances().getDaoSession()
                .insertOrReplace(userBean);
    }

    /**
     * 批量增加
     */
    public static void inserCountryList(List<UserBean> userBeans) {
        App.getInstances().getDaoSession()
                .getUserBeanDao()
                .insertOrReplaceInTx(userBeans);
    }

    /**
     * 根据ID删除
     *
     * @param id id
     */
    public static void deleteCountry(long id) {
        App.getInstances().getDaoSession()
                .getUserBeanDao()
                .deleteByKey(id);
    }

    /**
     * 查询所有
     *
     * @return List
     */
    public static List<UserBean> queryAllCountry() {
        return App.getInstances().getDaoSession().loadAll(UserBean.class);
    }

    /**
     * 查询去重班级
     *
     * @return List
     */
    public static ArrayList<String> queryClazz() {
        DaoSession session = App.getInstances().getDaoSession();
        ArrayList<String> clazz = new ArrayList<>();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT " + UserBeanDao.Properties.Clazz.columnName + " FROM USER_BEAN";
        Cursor c = session.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try {
            if (c.moveToFirst()) {
                do {
                    clazz.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return clazz;
    }

    /**
     * 查询去重教师
     *
     * @return List
     */
    public static ArrayList<String> queryTeacher() {
        DaoSession session = App.getInstances().getDaoSession();
        ArrayList<String> teacher = new ArrayList<>();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT " + UserBeanDao.Properties.Teacher.columnName + " FROM USER_BEAN";
        Cursor c = session.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try {
            if (c.moveToFirst()) {
                do {
                    teacher.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return teacher;
    }

    /**
     * 查询去重节次
     *
     * @return List
     */
    public static ArrayList<String> queryNumber() {
        DaoSession session = App.getInstances().getDaoSession();
        ArrayList<String> clazz = new ArrayList<>();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT " + UserBeanDao.Properties.Tiems.columnName + " FROM USER_BEAN";
        Cursor c = session.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try {
            if (c.moveToFirst()) {
                do {
                    clazz.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return clazz;
    }

    /**
     * 查询去重周次
     *
     * @return List
     */
    public static ArrayList<String> queryWeektimes() {
        DaoSession session = App.getInstances().getDaoSession();
        ArrayList<String> clazz = new ArrayList<>();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT " + UserBeanDao.Properties.Weekendtime.columnName + " FROM USER_BEAN";
        Cursor c = session.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try {
            if (c.moveToFirst()) {
                do {
                    clazz.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return clazz;
    }

    /**
     * 查询去重星期
     *
     * @return List
     */
    public static ArrayList<String> queryWeek() {
        DaoSession session = App.getInstances().getDaoSession();
        ArrayList<String> clazz = new ArrayList<>();
        String SQL_DISTINCT_ENAME = "SELECT DISTINCT " + UserBeanDao.Properties.Weekennd.columnName + " FROM USER_BEAN";
        Cursor c = session.getDatabase().rawQuery(SQL_DISTINCT_ENAME, null);
        try {
            if (c.moveToFirst()) {
                do {
                    clazz.add(c.getString(0));
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return clazz;
    }

    /**
     * 条件查询
     *
     * @return List
     */
    public static ArrayList<UserBean> querySubmit(String clazzes, String weektime, String week, String tiems, String teacher) {
        DaoSession session = App.getInstances().getDaoSession();
        ArrayList<UserBean> clazz = new ArrayList<>();
        List<String> mQuery = new ArrayList<>();
        /*底下为动态sql语句*/
        String SQL_QUERY = "SELECT * FROM USER_BEAN where ";
        if (!TextUtils.isEmpty(clazzes)) {
            mQuery.add(clazzes);
            SQL_QUERY = SQL_QUERY + UserBeanDao.Properties.Clazz.columnName + " = ? ";
        }

        if (!TextUtils.isEmpty(weektime)) {
            String head = SQL_QUERY.substring(SQL_QUERY.length() - 3, SQL_QUERY.length());
            mQuery.add(weektime);
            if (head.equals(" ? ")) {
                SQL_QUERY = SQL_QUERY + "and " + UserBeanDao.Properties.Weekendtime.columnName + " = ? ";
            } else {
                SQL_QUERY = SQL_QUERY + UserBeanDao.Properties.Weekendtime.columnName + " = ? ";

            }
        }

        if (!TextUtils.isEmpty(week)) {
            mQuery.add(week);
            String head = SQL_QUERY.substring(SQL_QUERY.length() - 3, SQL_QUERY.length());
            if (head.equals(" ? ")) {
                SQL_QUERY = SQL_QUERY + "and " + UserBeanDao.Properties.Weekennd.columnName + " = ? ";
            } else {
                SQL_QUERY = SQL_QUERY + UserBeanDao.Properties.Weekennd.columnName + " = ? ";

            }
        }

        if (!TextUtils.isEmpty(tiems)) {
            mQuery.add(tiems);
            String head = SQL_QUERY.substring(SQL_QUERY.length() - 3, SQL_QUERY.length());
            if (head.equals(" ? ")) {
                SQL_QUERY = SQL_QUERY + "and " + UserBeanDao.Properties.Tiems.columnName + " = ? ";
            } else {
                SQL_QUERY = SQL_QUERY + UserBeanDao.Properties.Tiems.columnName + " = ? ";

            }
        }

        if (!TextUtils.isEmpty(teacher)) {
            mQuery.add(teacher);
            String head = SQL_QUERY.substring(SQL_QUERY.length() - 3, SQL_QUERY.length());
            if (head.equals(" ? ")) {
                SQL_QUERY = SQL_QUERY + "and " + UserBeanDao.Properties.Teacher.columnName + " = ? ";
            } else {
                SQL_QUERY = SQL_QUERY + UserBeanDao.Properties.Teacher.columnName + " = ? ";

            }
        }

        /*到这里*/
        String[] ways = new String[mQuery.size()];
        for (int i = 0; i < mQuery.size(); i++) {
            ways[i] = mQuery.get(i);
        }
        Cursor c = session.getDatabase().rawQuery(SQL_QUERY, ways);
        try {
            if (c.moveToFirst()) {
                do {
                    String id = c.getString(0);
                    String dt = c.getString(1);
                    String wt = c.getString(2);
                    String wk = c.getString(3);
                    String ts = c.getString(4);
                    String cz = c.getString(5);
                    String nr = c.getString(6);
                    String ap = c.getString(7);
                    String ca = c.getString(8);
                    String tr = c.getString(9);
                    String pm = c.getString(10);
                    UserBean userBean = new UserBean(Long.parseLong(id), dt, wt, wk, ts, cz, nr, ap, ca, tr, pm);
                    clazz.add(userBean);
                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return clazz;
    }

}
