package com.example.query.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Time: 2019/1/2
 **/

@Entity
public class UserBean {
    @Id(autoincrement = true)
    private Long id;//主键递增
    private String date;//日期
    private String weekendtime;//周次
    private String weekennd;//星期
    private String tiems;//节次
    private String clazz;//班级
    private String numer;//人数
    private String app;//所需软件
    private String classname;//课程名称
    private String teacher;//授课教师
    private String pcroom;//机房

    @Generated(hash = 623302242)
    public UserBean(Long id, String date, String weekendtime, String weekennd,
                    String tiems, String clazz, String numer, String app, String classname,
                    String teacher, String pcroom) {
        this.id = id;
        this.date = date;
        this.weekendtime = weekendtime;
        this.weekennd = weekennd;
        this.tiems = tiems;
        this.clazz = clazz;
        this.numer = numer;
        this.app = app;
        this.classname = classname;
        this.teacher = teacher;
        this.pcroom = pcroom;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekendtime() {
        return this.weekendtime;
    }

    public void setWeekendtime(String weekendtime) {
        this.weekendtime = weekendtime;
    }

    public String getWeekennd() {
        return this.weekennd;
    }

    public void setWeekennd(String weekennd) {
        this.weekennd = weekennd;
    }

    public String getTiems() {
        return this.tiems;
    }

    public void setTiems(String tiems) {
        this.tiems = tiems;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getNumer() {
        return this.numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPcroom() {
        return this.pcroom;
    }

    public void setPcroom(String pcroom) {
        this.pcroom = pcroom;
    }


}
