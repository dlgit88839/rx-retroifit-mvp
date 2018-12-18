package com.bling.fastdev.network.exception;

/**
 * 服务器返回错误（参数异常，验证失效等）
 *  dongliang
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
