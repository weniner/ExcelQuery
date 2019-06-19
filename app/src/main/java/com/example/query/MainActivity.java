package com.example.query;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.query.gen.DaoMaster;
import com.example.query.model.UserBean;
import com.example.query.model.ViewModel;
import com.example.query.utils.Daoutils;
import com.example.query.utils.WriteUtils;
import com.example.query.view.ViewActivity;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_submit;
    private TextView tv_weektimes;
    private TextView tv_week;
    private TextView tv_clazz;
    private TextView tv_number;
    private TextView tv_teacher;
    private List<UserBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();//初始化控件
        getPermisson();//获取权限
        new Thread(new WorkRunable()).start();//开启线程
        Toast.makeText(MainActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();

    }

    private void init() {
        btn_submit = findViewById(R.id.btn_submit);
        tv_weektimes = findViewById(R.id.tv_weektimes);
        tv_week = findViewById(R.id.tv_week);
        tv_clazz = findViewById(R.id.tv_clazz);
        tv_number = findViewById(R.id.tv_number);
        tv_teacher = findViewById(R.id.tv_teaccher);

        btn_submit.setOnClickListener(this);
        tv_clazz.setOnClickListener(this);
        tv_teacher.setOnClickListener(this);
        tv_number.setOnClickListener(this);
        tv_week.setOnClickListener(this);
        tv_weektimes.setOnClickListener(this);
    }

    /**
     * 监听点击事件
     *
     * @param v 获取点击
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submit();//查询按钮
                break;
            case R.id.tv_clazz:
                clazz();
                break;
            case R.id.tv_number:
                number();
                break;
            case R.id.tv_teaccher:
                teacher();
                break;
            case R.id.tv_week:
                week();
                break;
            case R.id.tv_weektimes:
                weektimes();
                break;
            default:
                break;
        }
    }


    /**
     * 初始化数据库并存入数据
     */
    private class WorkRunable implements Runnable {
        @Override
        public void run() {
            WriteUtils writeUtils = new WriteUtils(MainActivity.this);
            Daoutils.inserCountryList(writeUtils.readExcel());

        }
    }

    /**
     * 选周次
     */
    private void weektimes() {
        ArrayList<String> mlist = Daoutils.queryWeektimes();
        String[] courses = new String[mlist.size() + 1];
        courses[0] = "不限";
        for (int i = 0; i < mlist.size(); i++) {
            courses[i + 1] = mlist.get(i);
        }
        OptionPicker picker = new OptionPicker(MainActivity.this, courses);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(Color.BLUE, 40);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                tv_weektimes.setText(item);
                SharedPreferences.Editor editor = getSharedPreferences("erweima", MODE_PRIVATE).edit();
                editor.putString("course", item);
                editor.apply();

            }
        });
        picker.show();
    }

    /**
     * 选周
     */
    private void week() {
        ArrayList<String> mlist = Daoutils.queryWeek();
        String[] courses = new String[mlist.size() + 1];
        courses[0] = "不限";
        for (int i = 0; i < mlist.size(); i++) {
            courses[i + 1] = mlist.get(i);
        }
        OptionPicker picker = new OptionPicker(MainActivity.this, courses);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(Color.BLUE, 40);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                tv_week.setText(item);
                SharedPreferences.Editor editor = getSharedPreferences("erweima", MODE_PRIVATE).edit();
                editor.putString("course", item);
                editor.apply();

            }
        });
        picker.show();
    }

    /**
     * 选教师
     */
    private void teacher() {
        ArrayList<String> mlist = Daoutils.queryTeacher();
        String[] courses = new String[mlist.size() + 1];
        courses[0] = "不限";
        for (int i = 0; i < mlist.size(); i++) {
            courses[i + 1] = mlist.get(i);
        }
        OptionPicker picker = new OptionPicker(MainActivity.this, courses);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(Color.BLUE, 40);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                tv_teacher.setText(item);
                SharedPreferences.Editor editor = getSharedPreferences("erweima", MODE_PRIVATE).edit();
                editor.putString("course", item);
                editor.apply();

            }
        });
        picker.show();
    }

    /**
     * 选节次
     */
    private void number() {
        ArrayList<String> mlist = Daoutils.queryNumber();
        String[] courses = new String[mlist.size() + 1];
        courses[0] = "不限";
        for (int i = 0; i < mlist.size(); i++) {
            courses[i + 1] = mlist.get(i);
        }
        OptionPicker picker = new OptionPicker(MainActivity.this, courses);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(Color.BLUE, 40);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                tv_number.setText(item);
                SharedPreferences.Editor editor = getSharedPreferences("erweima", MODE_PRIVATE).edit();
                editor.putString("course", item);
                editor.apply();

            }
        });
        picker.show();
    }

    /**
     * 选班级
     */
    private void clazz() {
        ArrayList<String> mlist = Daoutils.queryClazz();
        String[] courses = new String[mlist.size() + 1];
        courses[0] = "不限";
        for (int i = 0; i < mlist.size(); i++) {
            courses[i + 1] = mlist.get(i);
        }
        OptionPicker picker = new OptionPicker(MainActivity.this, courses);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(Color.BLUE, 40);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                tv_clazz.setText(item);
                SharedPreferences.Editor editor = getSharedPreferences("erweima", MODE_PRIVATE).edit();
                editor.putString("course", item);
                editor.apply();

            }
        });
        picker.show();

    }

    /**
     * 提交
     */
    private void submit() {
        String clazz = tv_clazz.getText().toString().trim();
        String weektimes = tv_weektimes.getText().toString().trim();
        String week = tv_week.getText().toString().trim();
        String teacher = tv_teacher.getText().toString().trim();
        String number = tv_number.getText().toString().trim();
        if (clazz.equals("不限")) {
            clazz = "";
        }
        if (weektimes.equals("不限")) {
            weektimes = "";
        }
        if (week.equals("不限")) {
            week = "";
        }
        if (teacher.equals("不限")) {
            teacher = "";
        }
        if (number.equals("不限")) {
            number = "";
        }

        if (clazz.equals("------请选择班级------") || week.equals("------请选择星期------")
                || weektimes.equals("------请选择周次------")
                || teacher.equals("------请选择教师------")
                || number.equals("------请选择节次------")) {//当没选择任何时
            //Toast.makeText(MainActivity.this, "请选择后再查询", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(clazz) && TextUtils.isEmpty(week) && TextUtils.isEmpty(weektimes)
                && TextUtils.isEmpty(teacher) && TextUtils.isEmpty(number)) {
            mList = Daoutils.queryAllCountry();
        } else {//当选择部分时
            mList = Daoutils.querySubmit(clazz, weektimes, week, number, teacher);
        }
        if (mList.size() == 0) {
            Log.d("MainActivity", "size = 0");
            Toast.makeText(MainActivity.this, "请核实后再查询", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, ViewActivity.class);
            intent.putExtra("clazz", clazz);
            intent.putExtra("week", week);
            intent.putExtra("weektimes", weektimes);
            intent.putExtra("teacher", teacher);
            intent.putExtra("number", number);
            startActivity(intent);//跳转查看界面
        }
    }


    /**
     * 检查权限
     */
    private void getPermisson() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    /**
     * 权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //同意权限申请

                } else { //拒绝权限申请
                    Toast.makeText(this, "权限被拒绝了，无法正常使用", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

}
