package com.bling.fastdev.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bling.fastdev.R;


/**
 * Created by dongliang on 2016/1/22 0022.
 * 加载动画
 */
public class CustomProgressDialog extends Dialog {
    private Context context;
    private String content;
    private TextView tvLoadingDialog;

    public CustomProgressDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomProgressDialog(Context context, String content) {
        super(context, R.style.CustomDialog);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setCanceledOnTouchOutside(false);
        super.onCreate(savedInstanceState);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_pb_dialog, null);
        setContentView(view);
        setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        tvLoadingDialog = (TextView) view.findViewById(R.id.tv_loading_content);
        tvLoadingDialog.setText(content);

    }

    /**
     * 设置内容
     * @param title
     */
    public void setTitle(String title) {
        tvLoadingDialog.setText(title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return event.getAction() == KeyEvent.KEYCODE_BACK;
    }
}