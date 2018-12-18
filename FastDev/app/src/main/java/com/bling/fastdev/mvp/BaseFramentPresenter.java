package com.bling.fastdev.mvp;


import android.os.Bundle;

import com.bling.fastdev.base.MvpBaseFragment;


import java.lang.ref.WeakReference;

/**
 * 用于配合 rxliftcycle 所以 根据basePresenter 扩展成 BaseActivityPresenter 和 BaseFragmentPresenter 管理RxJava生命周期
 * created by dongliang
 *  2018/4/17  10:15
 */
public class BaseFramentPresenter<V extends IBaseView> extends BasePresenter<V> {

    private WeakReference<MvpBaseFragment> fragmentRef;

    public void attachActivity(MvpBaseFragment fragment) {
        fragmentRef = new WeakReference<>(fragment);
    }


    protected MvpBaseFragment getFragment() {
        return fragmentRef == null ? null : fragmentRef.get();
    }


    protected void detachFragment() {
        if (fragmentRef != null) {
            fragmentRef.clear();
            fragmentRef = null;
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        detachLocalSource();
        detachNetSource();
        detachFragment();
    }


    public void onSaveInstanceState(Bundle savedInstanceState) {

    }


    public void onStart() {

    }


    public void onResume() {

    }


    public void onReStart() {

    }


    public void onStop() {

    }


    public void onPause() {

    }


    public void onDestroy() {

    }


}
