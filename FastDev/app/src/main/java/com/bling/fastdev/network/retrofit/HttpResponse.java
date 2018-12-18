package com.bling.fastdev.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * http响应参数实体类
 * 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * 备注:这里与服务器约定返回格式
 *
 */
public class  HttpResponse<T> {
public static  final  int  CORRECT_CODE=0;
    /**
     * 描述信息
     */
    @SerializedName("msg")
    private String msg;

    /**
     * 状态码
     */
    @SerializedName("code")
    private int code;

    /**
     * 数据对象[成功返回对象,失败返回错误说明]
     */
    @SerializedName("data")
    private T data;

    /**
     * 判断返回的数据是否正常 code=0 如果code=其他 表示返回为错误信息
     * @return
     */

    public boolean isSuccess() {
        return code == CORRECT_CODE ? true : false;
    }

    public String toString() {
        String response =  "{\"code\": " + code + ",\"msg\":" + msg + ",\"result\":" + new Gson().toJson(data) + "}";
        return response;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T data) {
        this.data = data;
    }
}
