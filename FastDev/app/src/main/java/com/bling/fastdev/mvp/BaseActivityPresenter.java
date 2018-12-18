package com.bling.fastdev.mvp;


import android.os.Bundle;

import com.bling.fastdev.base.MvpBaseActivity;


import java.lang.ref.WeakReference;

/**
 * 用于配合 rxliftcycle 所以 根据basePresenter 扩展成 BaseActivityPresenter 和 BaseFragmentPresenter 管理RxJava生命周期
 * created by dongliang
 *  2018/4/17  10:15
 */
public class BaseActivityPresenter<V extends IBaseView> extends BasePresenter<V> {

    private WeakReference<MvpBaseActivity> activityRef;

    public void attachActivity(MvpBaseActivity activity) {
        activityRef = new WeakReference<>(activity);
    }


    protected MvpBaseActivity getActivity() {
        return activityRef == null ? null : activityRef.get();
    }


    protected void detachActivity() {
        if (activityRef != null) {
            activityRef.clear();
            activityRef = null;
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        detachLocalSource();
        detachNetSource();
        detachActivity();
    }


    public void onSaveInstanceState(Bundle savedInstanceState) {

    }


    public void onStart() {

    }


    public void onResume() {

    }


    public void onReStart() {

    }


    public void onStop() { }


    public void onPause() { }


    public void onDestroy() { }

}
