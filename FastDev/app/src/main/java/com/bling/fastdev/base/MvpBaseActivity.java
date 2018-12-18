package com.bling.fastdev.base;

import android.os.Bundle;

import com.bling.fastdev.mvp.BaseActivityPresenter;
import com.bling.fastdev.mvp.BaseView;


public abstract class MvpBaseActivity<P extends BaseActivityPresenter> extends BaseActivity implements BaseView {
    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);

    }

    protected abstract P createPresenter();


    protected void initPresenter() {
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
            presenter.attachActivity(this);
        }
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter!=null){
            presenter.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(presenter!=null){
            presenter.onPause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (presenter!=null){
            presenter.onReStart();
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
            presenter.onDestroy();
        }
        super.onDestroy();
    }
}
