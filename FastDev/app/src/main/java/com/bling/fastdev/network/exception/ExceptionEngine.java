package com.bling.fastdev.network.exception;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * RXJava retroifit 网络请求   onError错误/异常处理工具
 *
 */
public class ExceptionEngine {

    /**
     *错误码自行定义 与服务器返回码区分开
     * created by dongliang
     *  2018/4/9  14:46
     */
    public static final int UN_KNOWN_ERROR = 1000;//未知错误
    public static final int ANALYTIC_DATA_ERROR = 1001;//解析数据错误
    public static final int CONNECT_ERROR = 1002;//网络连接错误
    public static final int TIME_OUT_ERROR = 1003;//网络连接超时

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code());
            ex.setMsg("网络错误");  //均视为网络错误
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getCode());
            ex.setMsg(serverExc.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            ex = new ApiException(e, ANALYTIC_DATA_ERROR);
            ex.setMsg("解析错误");
            return ex;
        } else if (e instanceof ConnectException) {//连接网络错误
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg("连接失败");
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg("网络超时");
            return ex;
        } else {  //未知错误
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg("未知错误");
            return ex;
        }
    }

}
