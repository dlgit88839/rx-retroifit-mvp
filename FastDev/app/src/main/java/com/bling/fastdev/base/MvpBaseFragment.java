package com.bling.fastdev.base;

import android.os.Bundle;

import com.bling.fastdev.mvp.BaseFramentPresenter;
import com.bling.fastdev.mvp.BaseView;


abstract public class MvpBaseFragment<P extends BaseFramentPresenter> extends BaseFragment implements BaseView {

    P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    protected abstract P createPresenter();

    @SuppressWarnings("unchecked")
    protected void initPresenter() {
        presenter = createPresenter();
        presenter.attachView(this);
        presenter.attachActivity(this);
    }


    @Override
    public void showProgress(String s) {
        showCustomLoadBar(s);
    }

    @Override
    public void hideProgress() {
        hideCustomLoadBar();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(presenter!=null){
            presenter.onPause();
        }
    }



    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView( );
            presenter.onDestroy();
        }
        super.onDestroy();
    }

}
