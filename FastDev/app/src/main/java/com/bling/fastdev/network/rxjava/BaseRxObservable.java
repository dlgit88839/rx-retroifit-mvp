package com.bling.fastdev.network.rxjava;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.bling.fastdev.network.retrofit.HttpResponse;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;


/**
 * @author dongliang
 * 适用Retrofit网络请求Observable(被监听者)
 */
public class BaseRxObservable<T> {

    Observable<HttpResponse<T>> observable;

    public BaseRxObservable(Observable<HttpResponse<T>> observable) {

        this.observable = observable;
    }

    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    public Observable<HttpResponse<T>> getObservable() {
        this.observable = observable
                          .map(new NormalResultFunction())
                          .onErrorResumeNext(new ErrorResultFunction<>())
                          .compose(new NetThreadTransForm());
        return observable;
    }


    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * 传入LifecycleProvider自动管理生命周期,避免内存溢出
     */
    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    public Observable<HttpResponse<T>> getObservable(@NonNull LifecycleProvider lifecycle) {
        this.observable = observable
                          .map(new NormalResultFunction())
                          .compose(lifecycle.bindToLifecycle())//需要在这个位置添加
                          .onErrorResumeNext(new ErrorResultFunction<>())
                          .compose(new NetThreadTransForm());

        return observable;
    }


    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * 传入LifecycleProvider<ActivityEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity,RxAppCompatActivity,RxFragmentActivity
     */
    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    public Observable<HttpResponse<T>> getActivityObservable(@NonNull LifecycleProvider<ActivityEvent> lifecycle) {
        return  getActivityObservable(lifecycle, ActivityEvent.DESTROY);
    }


    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    public Observable<HttpResponse<T>> getActivityObservable(@NonNull LifecycleProvider<ActivityEvent> lifecycle, @NonNull ActivityEvent event) {
        this.observable = observable
                          .map(new NormalResultFunction())
                          .compose(lifecycle.bindUntilEvent(event))//需要在这个位置添加
                          .onErrorResumeNext(new ErrorResultFunction<>())
                          .compose(new NetThreadTransForm());

        return observable;
    }


    /**
     * 获取被监听者
     * data:网络请求参数
     * 传入LifecycleProvider<FragmentEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxFragment,RxDialogFragment
     */
    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    public Observable<HttpResponse<T>> getFragmentObservable(@NonNull LifecycleProvider<FragmentEvent> lifecycle) {

        return getFragmentObservable(lifecycle, FragmentEvent.DESTROY);
    }


    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    public Observable<HttpResponse<T>> getFragmentObservable(@NonNull LifecycleProvider<FragmentEvent> lifecycle, @NonNull FragmentEvent event) {
       this.observable= observable
                        .map(new NormalResultFunction())
                        .compose(lifecycle.bindUntilEvent(event))//需要在这个位置添加
                        .onErrorResumeNext(new ErrorResultFunction<>())
                        .compose(new NetThreadTransForm());

        return observable;
    }



}
