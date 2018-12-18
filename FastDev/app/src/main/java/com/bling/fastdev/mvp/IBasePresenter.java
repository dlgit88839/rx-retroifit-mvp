package com.bling.fastdev.mvp;

public interface IBasePresenter<V extends IBaseView> {

    /**
     * presenter 绑定view
     * created by dongliang
     * 2018/4/8  17:09
     */
    void attachView(V view);

    /**
     * presnter 与view 解绑
     * created by dongliang
     * 2018/4/8  17:09
     */
    void detachView();


}
