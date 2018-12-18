package com.bling.fastdev.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.File;

/**
 * Created by Dongliang on 2016/1/25 0025.
 * 用于获取版本信息和版本更新
 */
public class VersionUtil {
    /*
    获取当前版本名
     */
    public static String getVersionName(Context context) throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionName;
    }

    private static String getIMEI2() {
        try {
            String m_szDevIDShort = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
            return m_szDevIDShort;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 获取当前版本号
     * @param context
     * @return
     * @throws Exception
     */
    public static int getVersionCode(Context context) throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionCode;
    }
    /**
     * 安装APK
     * @param context
     * @param file
     */

    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //执行的数据类型
        intent.setDataAndType(FileUriProvider.getUriForFile(context,file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}