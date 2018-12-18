package com.bling.fastdev.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bling.fastdev.widget.CustomProgressDialog;
import com.trello.rxlifecycle2.components.support.RxFragment;


/**
 * Created by wuxiaobo on 2015/12/2 0002.
 * fragment基类
 */
abstract public class BaseFragment extends RxFragment {
    public Toast mToast;
    public BaseApplication app;
    public Context context;
    public BaseActivity activity;
    private CustomProgressDialog mCustomProgressDialog;
    public View view;
    public int viewId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.app = (BaseApplication) this.getActivity().getApplication();// init application
        this.activity = (BaseActivity) getActivity();
        viewId = setLayout();
        initBundle(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(viewId, container, false);
        initView();
        initData();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != mToast) {
            mToast.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    abstract int setLayout();

    abstract void initView();

    abstract void initData();
    /**
     * 对传入的bundle进行处理
     * created by dongliang
     *  2018/4/17  11:08
     */
    protected void initBundle(Bundle bundle) {

    }
    /**
     * 显示自定义ProgressBar，提示用户操作正在处理中
     *
     * @param content:要显示的提示内容包括ProgressBar和text
     */
    public void showCustomLoadBar(String content) {
        activity.showCustomLoadBar(content);

    }

    /**
     * 释放自定义ProgressBar，提示操作结果
     */
    public void hideCustomLoadBar() {

        activity.hideCustomLoadBar();
    }

    /**
     * 直接跳转到另一个Activity界面
     *
     * @param classObj ***Activity.class
     */
    public void jump(Class<?> classObj, boolean finish) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(getActivity(), classObj);
        startActivity(intent);
        if (finish) {
            {
                getActivity().finish();
            }
        }
    }
    /**
     * 直接跳转到另一个Activity，并带有bundle参数
     */
    public void jumpWithBundle(Class<?> classObj, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(getActivity(), classObj);
        intent.putExtras(params);
        startActivity(intent);
    }


}
