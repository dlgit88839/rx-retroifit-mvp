package com.bling.fastdev.util;

import android.util.Log;

import com.bling.fastdev.BuildConfig;


/**
 * Created by dongliang on 2018/3/22
 * Log类封装
 */
public class Dlog {

    /**
     * log所在文件名即log信息打印时的TAG，如***.java
     */
    private static String className;
    /**
     * log信息所在的 方法名
     */
    private static String methodName;
    /**
     * log打印信息的行号
     */
    private static int lineNumber;

    /**
     * 格式化Log内容信息
     *
     * @param log log信息
     * @return string
     */
    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    /**
     * 获取Log信息所在的文件名、方法名、以及行号，以便迅速定位
     *
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    /**
     * 输出Log.e信息
     *
     */
    public static void e(String message) {
        if (BuildConfig.DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, createLog(message));
        }
    }

    /**
     * 内部调试bug用
     *
     */
    public static void i(String message) {
        if (BuildConfig.DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(message));
        }
    }

    /**
     * 内部调试bug用
     *
     */
    public static void w(String message) {
        if (BuildConfig.DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(message));
        }
    }
}
