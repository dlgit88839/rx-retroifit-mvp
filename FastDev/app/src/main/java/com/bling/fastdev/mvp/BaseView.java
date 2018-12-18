package com.bling.fastdev.mvp;


public interface BaseView extends IBaseView {


    /**
     * 跳转到登录页面
     * created by dongliang
     *  2018/6/21  14:13
     */
//    void accountLogout(String s);

    void showProgress(String s);

    void hideProgress();

}
