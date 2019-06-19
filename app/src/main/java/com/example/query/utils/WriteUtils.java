package com.example.query.utils;

import android.content.Context;

import com.example.query.model.UserBean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @Time: 2019/1/2
 **/
public class WriteUtils {
    private Context context;

    public WriteUtils(Context mContext) {
        this.context = mContext;
    }

    /**
     * 读取Excel数据
     *
     * @return List<Country>
     */
    public  List<UserBean> readExcel() {
        List<UserBean> userBeanList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("db.xls");
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            for (int i = 1; i < Rows; ++i) {
                //将每一列的数据读取
                String id = (sheet.getCell(0, i)).getContents();
                String data = (sheet.getCell(1, i)).getContents();
                String weekendtime = (sheet.getCell(2, i)).getContents();
                String weekennd = (sheet.getCell(3, i)).getContents();
                String tiems = (sheet.getCell(4, i)).getContents();
                String clazz = (sheet.getCell(5, i)).getContents();
                String numer = (sheet.getCell(6, i)).getContents();
                String app = (sheet.getCell(7, i)).getContents();
                String classname = (sheet.getCell(8, i)).getContents();
                String teacher = (sheet.getCell(9, i)).getContents();
                String pcroom = (sheet.getCell(10, i)).getContents();
                userBeanList.add(new UserBean(Long.parseLong(id),data,weekendtime,weekennd,tiems,
                        clazz,numer,app,classname,teacher,pcroom));
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBeanList;
    }

}
