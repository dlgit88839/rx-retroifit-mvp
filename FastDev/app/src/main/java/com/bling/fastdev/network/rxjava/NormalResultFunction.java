package com.bling.fastdev.network.rxjava;


import com.bling.fastdev.network.exception.ServerException;
import com.bling.fastdev.network.retrofit.HttpResponse;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * 服务器结果处理函数
 *
 */
public class NormalResultFunction implements Function<HttpResponse, Object> {
    @Override
    public Object apply(@NonNull HttpResponse response) throws Exception {

        if (!response.isSuccess()) { //如果数据请求异常 则抛出异常交由 observer error 处理
            throw new ServerException(response.getCode(), response.getMsg());
        }
        return response.getResult();
    }
}
