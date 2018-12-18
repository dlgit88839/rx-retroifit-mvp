package com.bling.fastdev.mvp;




import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected WeakReference<V> viewRef;       //软引用

    public V getView() {
        return viewRef.get();
    }

    /**
     * 异步任务时 检测view attach
     * created by dongliang
     * 2018/4/8  17:42
     */
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * 释放网络和本地资源
     * created by dongliang
     * 2018/6/25  10:12
     */
    protected void detachNetSource() {}

    protected boolean detachLocalSource() {
        return true;
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }


}
