package com.bling.fastdev.network.exception;

/**
 * api异常统一处理类(包括 网络异常 解析异常等 还包括code码错误)
 * 异常=[程序异常,网络异常,解析异常..]
 * 错误=[接口逻辑错误：code 不等于约定的值]
 *
 */
public class ApiException extends Exception {
    private int code;//错误码
    private String msg;//错误信息

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
