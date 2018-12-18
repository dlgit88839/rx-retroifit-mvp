package com.bling.fastdev.base;

import android.app.Application;

import com.bling.fastdev.network.retrofit.RetrofitUtils;
import com.squareup.leakcanary.LeakCanary;


public class BaseApplication extends Application {

    private static BaseApplication mInstance = null;
//    private DaoMaster.DevOpenHelper mHelper;
//    private SQLiteDatabase db;
//    private DaoMaster mDaoMaster;
//    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initRetroifit();
        LeakCanary.install(this);

    }

    /**
     * 初始化 http
     * created by dongliang
     * 2018/4/17  12:46
     */
    public void initRetroifit() {
        RetrofitUtils.init(getApplicationContext());
    }




//    /**
//     * 初始化 greendao 数据库
//     * created by dongliang
//     * 2018/4/17  12:46
//     */
//    public void initGreenDao() {
//
//        mHelper = new DaoMaster.DevOpenHelper(this, "terminal", null);
//        db = mHelper.getWritableDatabase();
//         // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
//        mDaoMaster = new DaoMaster(db);
//        mDaoSession = mDaoMaster.newSession();
//    }


    /**
     * application单例
     */
    public static BaseApplication getInstance() {
        return mInstance;
    }




}
