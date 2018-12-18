package com.bling.fastdev.network.rxjava;



import com.bling.fastdev.network.exception.ApiException;
import com.bling.fastdev.network.exception.ExceptionEngine;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * 适用Retrofit网络请求Observer(监听者)
 */
public abstract class BaseRxObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

        onStart(d);
    }

    @Override
    public void onNext(@NonNull T t) {

        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, ExceptionEngine.UN_KNOWN_ERROR));
        }
        onFinish();    //回调成功或者失败都调用 onFinish 可以用于隐藏progressbar
    }

    @Override
    public void onComplete() {
        onFinish();
    }


    protected abstract void onStart(Disposable d);

    /**
     * 错误/异常回调
     *
     * @author ZhongDaFeng
     */
    protected abstract void onError(ApiException e);

    /**
     * 事件成功回调
     */
    protected abstract void onSuccess(T response);


    /**
     * 事件完成回调
     */
    protected abstract void onFinish();

}
