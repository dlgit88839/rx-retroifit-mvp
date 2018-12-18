package com.bling.fastdev.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bling.fastdev.R;


/**
 * Created by dongliang on 2016/6/30.
 */
public class ToastUtils {
    public static Toast mToast = null;

    public static void toast(Context context, String message) {

        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (null == mToast) {
            mToast = new Toast(context.getApplicationContext());
            View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
            mToast.setView(view);
            mToast.setGravity(Gravity.BOTTOM, 0, ScreenUtils.getScreenHeight(context.getApplicationContext()) / 5);
            TextView textView = (TextView) mToast.getView().findViewById(R.id.tv_toast);
            textView.setText(message);
        } else {
            TextView textView = (TextView) mToast.getView().findViewById(R.id.tv_toast);
            textView.setText(message);

        }
        mToast.show();
    }


    /**
     * Toast对象封装
     *
     * @param resId String.xml
     */
    public static void toast(Context context, int resId) {
        String msg = context.getResources().getString(resId);
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        if (null == mToast) {
            mToast = new Toast(context.getApplicationContext());
            View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
            mToast.setView(view);
            mToast.setGravity(Gravity.BOTTOM, 0, ScreenUtils.getScreenHeight(context.getApplicationContext()) / 5);
            TextView textView = (TextView) mToast.getView().findViewById(R.id.tv_toast);
            textView.setText(msg);
        } else {
            TextView textView = (TextView) mToast.getView().findViewById(R.id.tv_toast);
            textView.setText(msg);
        }
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}