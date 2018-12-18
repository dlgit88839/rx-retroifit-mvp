package com.bling.fastdev.base;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.bling.fastdev.widget.CustomProgressDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * Created by dongliang on 2017/2/20 .
 * activity基类
 */
abstract public class BaseActivity extends RxAppCompatActivity {

    protected BaseApplication app;
    private CustomProgressDialog mCustomProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.app = (BaseApplication) getApplication();
        setTheme();
        setContentView(setLayout());
//        ButterKnife.bind(this);
        initView();
        initData();

    }

    //    手机更改字体大小时，不会影响App中字体的大小
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
    /**
     * 设置主题等
     * created by dongliang
     *  2018/10/31  15:25
     */
    protected void setTheme(){

    }

    /**
     * 初始化
     * created by dongliang
     * 2018/4/8  17:48
     */
    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData();


    /**
     * 显示自定义ProgressBar，提示用户操作正在处理中
     *
     * @param content:要显示的提示内容包括ProgressBar和text
     */
    public void showCustomLoadBar(String content) {
        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(this, content);
            mCustomProgressDialog.setCanceledOnTouchOutside(false);
        } else {
            mCustomProgressDialog.setTitle(content);
        }
        mCustomProgressDialog.show();
    }

    /**
     * 释放自定义ProgressBar，提示操作结果
     */
    public void hideCustomLoadBar() {
        if (mCustomProgressDialog != null && mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.cancel();
        }
    }


    /**
     * 直接跳转到另一个Activity界面
     *
     * @param classObj ***Activity.class
     * @param finish   是否关闭当前Activity
     */
    public void jump(Class<?> classObj, boolean finish) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        jump(intent, finish);
    }

    /**
     * 直接跳转到另一个Activity，并带有bundle参数
     *
     * @param classObj cls
     * @param params   bundle
     */
    public void jumpWithBundle(Class<?> classObj, Bundle params, boolean finish) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        jump(intent, finish);
    }

    /**
     * 以satartActivityForResult方式启动另一个Activity
     *
     * @param classObj    class对象
     * @param params      bundle参数
     * @param requestCode 请求码
     */
    public void reqJumpWithBundle(Class<?> classObj, Bundle params, int requestCode) {
        Intent intent = new Intent(this, classObj);
        intent.putExtras(params);
        jump(intent, requestCode);

    }

    /**
     * 以startActivityForResult方式启动另一个Activity
     *
     * @param classObj    class对象
     * @param requestCode 请求码
     */
    public void reqJump(Class<?> classObj, int requestCode) {
        Intent intent = new Intent(this, classObj);
        jump(intent, requestCode);
    }

    /**
     * startActivityForResult 通用方法
     * created by dongliang
     * 2018/4/9  11:39
     */
    public void jump(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivity 通用方法
     * created by dongliang
     * 2018/4/9  11:39
     */
    public void jump(Intent intent, boolean finish) {
        startActivity(intent);
        if (finish) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
