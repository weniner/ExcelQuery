package com.example.query.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.query.App;
import com.example.query.R;
import com.example.query.adapter.ViewAdapter;
import com.example.query.gen.DaoSession;
import com.example.query.gen.UserBeanDao;
import com.example.query.model.UserBean;
import com.example.query.model.ViewModel;
import com.example.query.utils.Daoutils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看信息
 */
public class ViewActivity extends AppCompatActivity {

    private List<ViewModel> mViewModels = new ArrayList<>();
    private List<UserBean> mUserBeans = new ArrayList<>();
    private ViewAdapter mAdapter;
    private ListView lv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        initView();//绑定控件，点击事件
        initLv();//初始化listview
    }

    private void initLv() {
        Intent intent = getIntent();
        /*获取所选择的值*/
        String clazz = intent.getStringExtra("clazz");
        String week = intent.getStringExtra("week");
        String weektime = intent.getStringExtra("weektimes");
        String teacher = intent.getStringExtra("teacher");
        String number = intent.getStringExtra("number");

        if (TextUtils.isEmpty(clazz) && TextUtils.isEmpty(week) && TextUtils.isEmpty(weektime)
                && TextUtils.isEmpty(teacher) && TextUtils.isEmpty(number)) {
            mUserBeans.clear();
            mUserBeans = Daoutils.queryAllCountry();//当全为空时
        } else {
            DaoSession session = App.getInstances().getDaoSession();
            mUserBeans.clear();
            mUserBeans = Daoutils.querySubmit(clazz, weektime, week, number, teacher);
        }
        for (int i = 0; i < mUserBeans.size(); i++) {
            String cw = mUserBeans.get(i).getClassname();
            String cz = mUserBeans.get(i).getClazz();
            String tr = mUserBeans.get(i).getTeacher();
            String wd = mUserBeans.get(i).getWeekennd();
            String we = mUserBeans.get(i).getWeekendtime();
            String pm = mUserBeans.get(i).getPcroom();
            String ts = mUserBeans.get(i).getTiems();
            String de = mUserBeans.get(i).getDate();
            String nr = mUserBeans.get(i).getNumer();
            String ap = mUserBeans.get(i).getApp();

            ViewModel viewModel = new ViewModel(i + "、日期：" + de + "    周次：" + we +
                    "\n星期：" + wd + "    节次：" + ts + "    班级：" + cz + "    人数：" + nr +
                    "\n所需软件：" + ap + "    课程名称：" + cw + "\n授课教师：" + tr + "    机房：" + pm);
            mViewModels.add(viewModel);
        }

        mAdapter = new ViewAdapter(this, R.layout.item_view, mViewModels);//设置迭代器
        lv_info.setAdapter(mAdapter);
    }

    private void initView() {
        lv_info = findViewById(R.id.lv_show);
    }
}
